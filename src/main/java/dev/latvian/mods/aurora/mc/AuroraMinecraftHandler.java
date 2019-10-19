package dev.latvian.mods.aurora.mc;

import dev.latvian.mods.aurora.Aurora;
import dev.latvian.mods.aurora.AuroraHomePageEvent;
import dev.latvian.mods.aurora.AuroraPageEvent;
import dev.latvian.mods.aurora.page.HomePageEntry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = Aurora.MOD_ID)
public class AuroraMinecraftHandler
{
	@SubscribeEvent
	public static void onHomeEvent(AuroraHomePageEvent event)
	{
		event.add(new HomePageEntry("Minecraft", "minecraft", "https://i.imgur.com/1aRpzK0.png")
				.add(new HomePageEntry("Online Players", "online_players", "https://i.imgur.com/a5dkvFu.png"))
				.add(new HomePageEntry("Online Players API", "online_players.json", "https://i.imgur.com/a5dkvFu.png"))
				.add(new HomePageEntry("World Info API", "world_info.json", "https://i.imgur.com/OVxZy1w.png"))
		);
	}

	@SubscribeEvent
	public static void onPageEvent(AuroraPageEvent event)
	{
		String[] s = event.getSplitUri();

		if (s[0].equals("minecraft"))
		{
			if (s.length == 1)
			{
				return;
			}

			if (s[1].equals("online_players"))
			{
				event.returnPage(new OnlinePlayersPage(event.getAuroraServer().getServer()));
			}
			else if (s[1].equals("online_players.json"))
			{
				event.returnPage(new OnlinePlayersJson(event.getAuroraServer().getServer()));
			}
			else if (s[1].equals("world_info.json"))
			{
				event.returnPage(new WorldInfoJson(event.getAuroraServer().getServer()));
			}
		}
	}
}