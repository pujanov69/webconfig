package com.hiclouds.webconfig.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return -1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String requestURL = ctx.getRequest().getRequestURL().toString();
		log.debug("ErrorFilter Request URL " + requestURL);
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		final Object throwable = ctx.get("throwable");

		log.debug("Redirecting to zuul custom error page");

		if (throwable instanceof ZuulException) {
			final ZuulException zuulException = (ZuulException) throwable;
			log.error("Zuul failure detected: " + zuulException.getMessage());

			// remove error code to prevent further error handling in follow up
			// filters
			ctx.remove("throwable");
			try {
				HttpServletRequest request = ctx.getRequest();
				String url = new StringBuilder().append("https://").append(request.getServerName()).toString();

				if (!"".equals(request.getContextPath())) {
					url += request.getContextPath();
				}
				ctx.getResponse().sendRedirect(url + "/zuul-proxy-error");
			} catch (IOException e) {
				log.error("Zuul Error Filter : Error while Zuul custom error page redirection " + e);
			}
		}
		return null;
	}
}