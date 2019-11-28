package com.example.todoservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class TodoServiceApplication implements WebMvcConfigurer {


	@Value("${management.endpoints.web.cors.max-age}")
	private long corsMaxAge;
	@Value("${management.endpoints.web.cors.allowed-headers}")
	private String[] corsAllowedHeaders;
	@Value("${management.endpoints.web.cors.allow-credentials}")
	private boolean corsAllowedCredentials;
	@Value("${management.endpoints.web.cors.allowed-methods}")
	private String[] corsAllowedMethods;
	@Value("${management.endpoints.web.cors.allowed-origins}")
	private String[] corsAllowedOrigins;


	private static final Logger logger = LoggerFactory.getLogger(TodoServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#
	 * addCorsMappings(org.springframework.web.servlet.config.annotation.
	 * CorsRegistry)
	 */
	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(this.corsAllowedOrigins).allowedMethods(this.corsAllowedMethods)
				.allowedHeaders(this.corsAllowedHeaders).allowCredentials(this.corsAllowedCredentials).maxAge(this.corsMaxAge);
	}
}
