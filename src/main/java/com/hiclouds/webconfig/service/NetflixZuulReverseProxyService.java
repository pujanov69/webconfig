package com.hiclouds.webconfig.service;

public interface NetflixZuulReverseProxyService {

	public void addReverseProxy(String uniqueId, String sourceUrl, String targetUrl);

}
