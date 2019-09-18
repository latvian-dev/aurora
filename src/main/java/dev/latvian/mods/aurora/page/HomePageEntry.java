package dev.latvian.mods.aurora.page;

/**
 * @author LatvianModder
 */
public class HomePageEntry
{
	public final String title;
	public final String url;
	public String icon;
	public boolean requiresSecure;

	public HomePageEntry(String t, String u)
	{
		title = t;
		url = u;
		icon = "";
		requiresSecure = false;
	}

	public HomePageEntry icon(String i)
	{
		icon = i;
		return this;
	}

	public HomePageEntry requiresSecure()
	{
		requiresSecure = true;
		return this;
	}
}