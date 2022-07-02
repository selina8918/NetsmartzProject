package com.students.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration

public class SwaggerConfig {
	
	    @Bean
	    public Docket docket() { 
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("Student")
	                .apiInfo(apiInfo()).select().paths(regex("/api.*"))
	                .build();
	    }
	    private ApiInfo apiInfo() { 
			return new ApiInfoBuilder().title("Student Management System")
					.description("The Documentation Generated using SWAGGER2 for our WebServices REST API")
					.termsOfServiceUrl("https://github.com/selina8918")
					.license("Selina8918_License")
					.licenseUrl("https://www.github.com/selina8918").version("1.0").build();
		} 
	   
        
}

