package com.hiclouds.webconfig.service.impl;

import com.hiclouds.webconfig.service.NetflixZuulReverseProxyService;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.stereotype.Service;

@Service
public class NetflixZuulReverseProxyServiceImpl implements NetflixZuulReverseProxyService {

	private ZuulProperties zuulProperties;
	private ZuulHandlerMapping zuulHandlerMapping;

	private static final Logger LOGGER = LoggerFactory
      .getLogger(NetflixZuulReverseProxyServiceImpl.class);

	@Autowired
	public NetflixZuulReverseProxyServiceImpl(final ZuulProperties zuulProperties,
			final ZuulHandlerMapping zuulHandlerMapping) {
		this.zuulProperties = zuulProperties;
		this.zuulHandlerMapping = zuulHandlerMapping;
	}

	@Override
	public void addReverseProxy(String uniqueId, String sourceUrl, String targetUrl) {
		zuulProperties.getRoutes().put(uniqueId,
				new ZuulRoute(uniqueId, sourceUrl, null, targetUrl, true, false, new HashSet<>()));
		zuulHandlerMapping.setDirty(true);
	}

}
