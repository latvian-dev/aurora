package dev.latvian.mods.aurora.tag;

import dev.latvian.mods.aurora.page.WebPage;

/**
 * @author LatvianModder
 */
public abstract class TagBase implements WebPage
{
	public PairedTag parent;

	public abstract boolean isEmpty();

	public abstract void build(StringBuilder builder);

	public String getAttribute(String key)
	{
		return "";
	}

	@Override
	public String getContent()
	{
		StringBuilder builder = new StringBuilder("<!DOCTYPE http>");
		build(builder);
		return builder.toString();
	}
}