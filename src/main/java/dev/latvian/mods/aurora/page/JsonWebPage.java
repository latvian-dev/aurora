package dev.latvian.mods.aurora.page;

import com.google.gson.JsonElement;

/**
 * @author LatvianModder
 */
public class JsonWebPage implements WebPage
{
	private final JsonElement element;

	public JsonWebPage(JsonElement e)
	{
		element = e;
	}

	@Override
	public String getContent()
	{
		return element.toString();
	}

	@Override
	public String getContentType()
	{
		return "application/json";
	}
}
