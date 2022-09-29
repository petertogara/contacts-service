package com.youtap.contacts.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Value("${youtap.contacts.service.httpconnecttimeout:10000}")
	private int httpConnectTimeOut;
	@Value("${youtap.contacts.service.httpreadtimeout:10000}")
	private int httpReadTimeOut;

	private ClientHttpRequestFactory getHttpClientRequestFactory() {

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(httpConnectTimeOut);
		clientHttpRequestFactory.setReadTimeout(httpReadTimeOut);
		return clientHttpRequestFactory;
	}

	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate(getHttpClientRequestFactory());
	}
}

