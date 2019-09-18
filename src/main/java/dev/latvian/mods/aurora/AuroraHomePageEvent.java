package dev.latvian.mods.aurora;

import dev.latvian.mods.aurora.page.HomePageEntry;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.function.Consumer;

/**
 * @author LatvianModder
 */
public class AuroraHomePageEvent extends Event
{
	private final AuroraServer server;
	private Consumer<HomePageEntry> callback;

	public AuroraHomePageEvent(AuroraServer s, Consumer<HomePageEntry> c)
	{
		server = s;
		callback = c;
	}

	public AuroraServer getAuroraServer()
	{
		return server;
	}

	public HomePageEntry add(String title, String url)
	{
		HomePageEntry entry = new HomePageEntry(title, url);
		callback.accept(entry);
		return entry;
	}
}