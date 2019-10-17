package dev.latvian.mods.aurora.mc;

import dev.latvian.mods.aurora.page.HTTPWebPage;
import dev.latvian.mods.aurora.tag.Tag;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

/**
 * @author LatvianModder
 */
public class OnlinePlayersPage extends HTTPWebPage
{
	private final MinecraftServer server;

	public OnlinePlayersPage(MinecraftServer s)
	{
		server = s;
	}

	@Override
	public String getTitle()
	{
		return "Minecraft";
	}

	@Override
	public String getDescription()
	{
		return "Online Players";
	}

	@Override
	public String getStylesheet()
	{
		return "";
	}

	@Override
	public boolean addBackButton()
	{
		return false;
	}

	@Override
	public void body(Tag body)
	{
		Tag playerTable = body.table().addClass("player_table");

		for (EntityPlayerMP player : server.getPlayerList().getPlayers())
		{
			String id = player.getUniqueID().toString().replace("-", "");
			Tag row = playerTable.tr();
			row.td().img("https://crafatar.com/avatars/" + id + "?size=16");
			row.td().a(player.getName(), "https://mcuuid.net/?q=" + id);
		}
	}
}