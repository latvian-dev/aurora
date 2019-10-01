package dev.latvian.mods.aurora.mc;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.aurora.page.JsonWebPage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

/**
 * @author LatvianModder
 */
public class OnlinePlayersJson extends JsonWebPage
{
	private final MinecraftServer server;

	public OnlinePlayersJson(MinecraftServer s)
	{
		server = s;
	}

	@Override
	public JsonElement getJson()
	{
		JsonObject json = new JsonObject();
		json.addProperty("max_players", server.getMaxPlayers());

		JsonArray players = new JsonArray();

		for (EntityPlayerMP player : server.getPlayerList().getPlayers())
		{
			JsonObject o = new JsonObject();
			o.addProperty("name", player.getName());
			o.addProperty("uuid", player.getUniqueID().toString());
			players.add(o);
		}

		json.add("players", players);
		return json;
	}
}