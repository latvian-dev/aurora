package dev.latvian.mods.aurora;

import dev.latvian.mods.aurora.page.WebPage;
import io.netty.handler.codec.http.FullHttpRequest;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
@Cancelable
public class AuroraPageEvent extends Event
{
	private final AuroraServer server;
	private final FullHttpRequest request;
	private final String uri;
	private final String[] splitUri;
	private WebPage page;

	public AuroraPageEvent(AuroraServer s, FullHttpRequest r, String u)
	{
		server = s;
		request = r;
		uri = u;
		splitUri = uri.split("/");
	}

	public AuroraServer getAuroraServer()
	{
		return server;
	}

	public FullHttpRequest getRequest()
	{
		return request;
	}

	public String getUri()
	{
		return uri;
	}

	public String[] getSplitUri()
	{
		return splitUri;
	}

	@Nullable
	public WebPage getPage()
	{
		return page;
	}

	public void setPage(WebPage p)
	{
		page = p;
	}

	public boolean isSecure()
	{
		return true;
	}
}