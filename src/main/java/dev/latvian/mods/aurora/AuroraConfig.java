package dev.latvian.mods.aurora;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author LatvianModder
 */
@Config(modid = Aurora.MOD_ID)
public class AuroraConfig
{
	@Config.Comment("Webserver port.")
	@Config.RangeInt(min = 1025, max = 65534)
	public static int port = 48574;

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(Aurora.MOD_ID))
		{
			ConfigManager.sync(Aurora.MOD_ID, Config.Type.INSTANCE);
		}
	}
}