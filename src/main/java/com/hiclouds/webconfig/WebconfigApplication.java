package com.hiclouds.webconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class WebconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebconfigApplication.class, args);
	}

}
