package dev.latvian.mods.aurora.page;

import com.google.gson.JsonElement;

/**
 * @author LatvianModder
 */
public abstract class JsonWebPage implements WebPage
{
	public abstract JsonElement getJson();

	@Override
	public String getContent()
	{
		return getJson().toString();
	}

	@Override
	public String getContentType()
	{
		return "application/json";
	}
}