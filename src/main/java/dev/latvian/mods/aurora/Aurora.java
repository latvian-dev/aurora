package dev.latvian.mods.aurora;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(
		modid = Aurora.MOD_ID,
		name = Aurora.MOD_NAME,
		version = Aurora.VERSION
)
public class Aurora
{
	public static final String MOD_ID = "aurora";
	public static final String MOD_NAME = "Aurora";
	public static final String VERSION = "0.0.0.aurora";

	private static AuroraServer server;

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		if (server == null)
		{
			server = new AuroraServer(event.getServer(), AuroraConfig.port);
			server.start();
		}
	}

	@Mod.EventHandler
	public void onServerStopping(FMLServerStoppingEvent event)
	{
		if (server != null)
		{
			server.shutdown();
			server = null;
		}
	}
}