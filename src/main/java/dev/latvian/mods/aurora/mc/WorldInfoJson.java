package dev.latvian.mods.aurora.mc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.aurora.page.JsonWebPage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

/**
 * @author LatvianModder
 */
public class WorldInfoJson extends JsonWebPage
{
	private final MinecraftServer server;

	public WorldInfoJson(MinecraftServer s)
	{
		server = s;
	}

	@Override
	public JsonElement getJson()
	{
		WorldServer w = server.getWorld(0);
		JsonObject json = new JsonObject();

		json.addProperty("local_time", w.getWorldTime());
		json.addProperty("total_time", w.getTotalWorldTime());
		json.addProperty("daytime", w.isDaytime());
		json.addProperty("raining", w.isRaining());
		json.addProperty("thundering", w.isThundering());
		json.addProperty("moon_phase", w.getMoonPhase());

		return json;
	}
}
