package dev.latvian.mods.aurora.page;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author LatvianModder
 */
public interface WebPage
{
	String getContent();

	default String getContentType()
	{
		return "text/html";
	}

	default HttpResponseStatus getStatus()
	{
		return HttpResponseStatus.OK;
	}

	default boolean getRequiresAuth()
	{
		return false;
	}
}