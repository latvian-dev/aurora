package dev.latvian.mods.aurora.page;

import dev.latvian.mods.aurora.AuroraHomePageEvent;
import dev.latvian.mods.aurora.AuroraServer;
import dev.latvian.mods.aurora.tag.Tag;
import net.minecraftforge.common.MinecraftForge;

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
		HomePageEntry entry = new HomePageEntry(getTitle(), "", getIcon());
		MinecraftForge.EVENT_BUS.post(new AuroraHomePageEvent(server, entry));
		entry.entries.sort(null);
		addEntries(body.ol().style("width", "max-content").style("margin", "auto"), entry, "");
	}

	private void addEntries(Tag list, HomePageEntry entry, String u)
	{
		for (HomePageEntry e : entry.entries)
		{
			Tag li = list.li();

			if (!e.icon.isEmpty())
			{
				li.img(e.icon).style("height", "1em");
			}

			li.a(e.title, u + "/" + e.url);

			if (!e.entries.isEmpty())
			{
				addEntries(list.ol(), e, u + "/" + e.url);
			}
		}
	}
}