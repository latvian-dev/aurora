package dev.latvian.mods.aurora.page;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author LatvianModder
 */
public class WebPageNotFound implements WebPage
{
	private final String uri;

	public WebPageNotFound(String u)
	{
		uri = u;
	}

	@Override
	public String getContent()
	{
		StringBuilder r = new StringBuilder("<!DOCTYPE http>");

		r.append("<http>");

		r.append("<head>");
		r.append("<title>FTB Utilities Management</title>");
		//r.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://kubejs.latvian.dev/style.css\">");
		//r.append("<link rel=\"icon\" href=\"https://kubejs.latvian.dev/logo_48.png\">");
		r.append("</head>");

		r.append("<body>");
		r.append("<h1>Error! Page ").append(uri).append(" not found</h1>");

		r.append("</body>");

		r.append("</http>");
		return r.toString();
	}

	@Override
	public HttpResponseStatus getStatus()
	{
		return HttpResponseStatus.NOT_FOUND;
	}
}