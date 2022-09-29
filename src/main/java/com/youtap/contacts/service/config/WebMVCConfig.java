package com.youtap.contacts.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Configuration
@EnableWebMvc
@Order(-1)
public class WebMVCConfig implements WebMvcConfigurer {

	public static final String RESOURCE_PATTERNS  = "/resources/**";
	public static final String RESOURCE_LOCATIONS = "/resources/";
	public static final String SWAGGER_UI         = "swagger-ui.html";
	public static final String WEB_JARS           = "/webjars/**";
	@Value("${youtap.contacts.service.meta.inf.resources.uri}")
	private             String metaInfClassPathResource;
	@Value("${youtap.contacts.service.meta.inf.resources.webjars.uri}")
	private             String metaInfClassPathResourceWebjars;

	@Override
	public void addCorsMappings(final CorsRegistry registry) {

		registry.addMapping("/**")
				.allowedOriginPatterns("*")
				.allowedMethods("*")
				.exposedHeaders(CONTENT_TYPE, ACCEPT)
				.allowCredentials(true);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
				.addResourceHandler(RESOURCE_PATTERNS)
				.addResourceLocations(RESOURCE_LOCATIONS);
		registry.addResourceHandler(SWAGGER_UI)
				.addResourceLocations(metaInfClassPathResource);
		registry.addResourceHandler(WEB_JARS)
				.addResourceLocations(metaInfClassPathResourceWebjars);
	}


}
