package com.hiclouds.webconfig.config;

import com.hiclouds.webconfig.service.NetflixZuulReverseProxyService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {

  @Value("${redirection.url}")
  private String redirectionUrl;

  @Autowired
  private NetflixZuulReverseProxyService zuulReverseProxyService;

  @PostConstruct
  public void configureRedirections() {
    for (int i = 45001; i<= 46000; i++) {
      String sourceUrl = "/" + i;
      String destinationUrl = redirectionUrl + ":" + i;

      zuulReverseProxyService.addReverseProxy("proxy" + i , sourceUrl, destinationUrl);
    }

    for (int i = 50001; i<= 55000; i++) {
      String sourceUrl = "/" + i;
      String destinationUrl = redirectionUrl + ":" + i;

      zuulReverseProxyService.addReverseProxy("proxy" + i , sourceUrl, destinationUrl);
    }

    for (int i = 55001; i<= 60000; i++) {
      String sourceUrl = "/" + i;
      String destinationUrl = redirectionUrl + ":" + i;

      zuulReverseProxyService.addReverseProxy("proxy" + i , sourceUrl, destinationUrl);
    }
  }
}
