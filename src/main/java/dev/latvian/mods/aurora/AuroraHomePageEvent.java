package dev.latvian.mods.aurora;

import dev.latvian.mods.aurora.page.HomePageEntry;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author LatvianModder
 */
public class AuroraHomePageEvent extends Event
{
	private final AuroraServer server;
	private HomePageEntry entry;

	public AuroraHomePageEvent(AuroraServer s, HomePageEntry e)
	{
		server = s;
		entry = e;
	}

	public AuroraServer getAuroraServer()
	{
		return server;
	}

	public void add(HomePageEntry e)
	{
		entry.add(e);
	}
}