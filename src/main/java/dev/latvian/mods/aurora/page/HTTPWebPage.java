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
		Tag http = new PairedTag("http");
		head(http.paired("head"));
		body(http.paired("body"));
		return http.getContent();
	}

	public abstract void head(Tag head);

	public abstract void body(Tag body);
}