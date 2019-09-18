package dev.latvian.mods.aurora;

import dev.latvian.mods.aurora.page.HomePageEntry;
import dev.latvian.mods.aurora.page.WebPage;
import dev.latvian.mods.aurora.page.WebPageNotFound;
import dev.latvian.mods.aurora.tag.PairedTag;
import dev.latvian.mods.aurora.tag.Tag;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class AuroraServer
{
	private final MinecraftServer server;
	private final int port;
	private ChannelFuture channel;
	private final EventLoopGroup masterGroup;
	private final EventLoopGroup slaveGroup;

	public AuroraServer(MinecraftServer s, int p)
	{
		server = s;
		port = p;
		masterGroup = new NioEventLoopGroup();
		slaveGroup = new NioEventLoopGroup();
	}

	public MinecraftServer getServer()
	{
		return server;
	}

	void start()
	{
		Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

		try
		{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(masterGroup, slaveGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				public void initChannel(final SocketChannel ch)
				{
					ch.pipeline().addLast("codec", new HttpServerCodec());
					ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
					ch.pipeline().addLast("request", new ChannelInboundHandlerAdapter()
					{
						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
						{
							if (msg instanceof FullHttpRequest)
							{
								FullHttpRequest request = (FullHttpRequest) msg;
								String uri = request.uri();
								WebPage page;

								while (uri.startsWith("/"))
								{
									uri = uri.substring(1);
								}

								while (uri.endsWith("/"))
								{
									uri = uri.substring(0, uri.length() - 1);
								}

								if (uri.isEmpty())
								{
									Tag http = new PairedTag("http");
									page = http;

									Tag head = http.paired("head");
									head.paired("title", "Aurora");

									Tag body = http.paired("body");

									Tag list = body.ol();

									List<HomePageEntry> entries = new ArrayList<>();
									MinecraftForge.EVENT_BUS.post(new AuroraHomePageEvent(AuroraServer.this, entries::add));
									entries.sort(null);

									for (HomePageEntry entry : entries)
									{
										Tag li = list.li();

										if (!entry.icon.isEmpty())
										{
											li.img(entry.icon);
										}

										li.a(entry.title, "/" + entry.url);
									}
								}
								else
								{
									AuroraPageEvent event = new AuroraPageEvent(AuroraServer.this, request, uri);
									MinecraftForge.EVENT_BUS.post(event);

									if (event.getPage() == null)
									{
										page = new WebPageNotFound(event.getUri());
									}
									else
									{
										page = event.getPage();
									}
								}

								String content = page.getContent();
								FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, page.getStatus(), Unpooled.copiedBuffer(content.getBytes()));

								if (HttpUtil.isKeepAlive(request))
								{
									response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
								}

								response.headers().set(HttpHeaderNames.CONTENT_TYPE, page.getContentType());
								response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.length());

								ctx.writeAndFlush(response);
							}
							else
							{
								super.channelRead(ctx, msg);
							}
						}

						@Override
						public void channelReadComplete(ChannelHandlerContext ctx)
						{
							ctx.flush();
						}

						@Override
						public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
						{
							ctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.copiedBuffer(cause.getMessage().getBytes())));
						}
					});
				}
			});

			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			channel = bootstrap.bind(port).sync();
		}
		catch (InterruptedException ignored)
		{
		}
	}

	void shutdown()
	{
		slaveGroup.shutdownGracefully();
		masterGroup.shutdownGracefully();

		try
		{
			channel.channel().closeFuture().sync();
		}
		catch (InterruptedException ignored)
		{
		}
	}
}