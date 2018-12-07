package com.articles.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// Production closes swagger
	@Value("${swagger.enable}")
	private boolean enableSwagger;

	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.articles.controller")).paths(regex("/.*")).build()
				.enable(enableSwagger).apiInfo(metaData());
	}

	private ApiInfo metaData() {

		return new ApiInfo("Playground Authentication and Authorization", "Playground API Services for all Authentication and Authorization", "2.0",
				"https://www.altimetrik.com/privacy-policy/", new Contact("Playground", "https://playground.altimetrik.com", "pg-mgr1@altimetrik.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

}
