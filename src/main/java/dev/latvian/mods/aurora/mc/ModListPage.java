package dev.latvian.mods.aurora.mc;

import dev.latvian.mods.aurora.AuroraConfig;
import dev.latvian.mods.aurora.PageType;
import dev.latvian.mods.aurora.page.HTTPWebPage;
import dev.latvian.mods.aurora.tag.Style;
import dev.latvian.mods.aurora.tag.Tag;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.Set;

/**
 * @author LatvianModder
 */
public class ModListPage extends HTTPWebPage
{
	private final Set<String> excludedMods;

	public ModListPage(Set<String> set)
	{
		excludedMods = set;
	}

	@Override
	public String getTitle()
	{
		return "Minecraft";
	}

	@Override
	public String getDescription()
	{
		return "Mod List";
	}

	@Override
	public PageType getPageType()
	{
		return AuroraConfig.modlist_page;
	}

	@Override
	public void head(Tag head)
	{
		super.head(head);
		Style s = head.style();
		s.add("span.num").set("margin-right", "0.8em");
	}

	@Override
	public void body(Tag body)
	{
		Tag table = body.table();
		Tag row = table.tr();
		row.th().text("Mod List");
		row.th().text("Version");

		int i = 0;

		for (ModContainer container : Loader.instance().getModList())
		{
			if (!excludedMods.contains(container.getModId()))
			{
				row = table.tr();
				Tag t = row.td();
				t.span(String.valueOf(++i), "num");
				t.a(container.getName(), "/modlist/" + container.getModId());
				row.td().text(container.getDisplayVersion());
			}
		}
	}
}