package dev.latvian.mods.aurora.page;

import dev.latvian.mods.aurora.tag.PairedTag;
import dev.latvian.mods.aurora.tag.Tag;

/**
 * @author LatvianModder
 */
public abstract class HTTPWebPage implements WebPage
{
	@Override
	public String getContent()
	{
		Tag http = new PairedTag("html");
		Tag head = http.paired("head");
		head.unpaired("meta").attr("charset", "UTF-8");
		head(head);
		body(http.paired("body"));
		return http.getContent();
	}

	public String getTitle()
	{
		return "Aurora";
	}

	public String getDescription()
	{
		return "";
	}

	public String getStylesheet()
	{
		return "https://kubejs.latvian.dev/style.css";
	}

	public String getIcon()
	{
		return "https://files.latmod.com/public/aurora.gif";
	}

	public void head(Tag head)
	{
		head.paired("title", getTitle());

		String d = getDescription();

		if (!d.isEmpty())
		{
			head.meta("description", d);
		}

		head.unpaired("link").attr("rel", "icon").attr("href", getIcon());

		String s = getStylesheet();

		if (!s.isEmpty())
		{
			head.unpaired("link").attr("rel", "stylesheet").attr("type", "text/css").attr("href", s);
		}

		head.meta("viewport", "width=device-width, initial-scale=1.0");
	}

	public void body(Tag body)
	{
	}
}