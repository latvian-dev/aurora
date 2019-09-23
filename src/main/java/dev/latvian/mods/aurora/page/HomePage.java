package dev.latvian.mods.aurora.page;

import dev.latvian.mods.aurora.AuroraHomePageEvent;
import dev.latvian.mods.aurora.AuroraServer;
import dev.latvian.mods.aurora.tag.Tag;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class HomePage extends HTTPWebPage
{
	public final AuroraServer server;

	public HomePage(AuroraServer s)
	{
		server = s;
	}

	@Override
	public String getTitle()
	{
		return "Aurora";
	}

	@Override
	public String getDescription()
	{
		return "Index";
	}

	@Override
	public void body(Tag body)
	{
		Tag list = body.ol();

		List<HomePageEntry> entries = new ArrayList<>();
		MinecraftForge.EVENT_BUS.post(new AuroraHomePageEvent(server, entries::add));
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
}