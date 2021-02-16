package com.restful.gestaodepedidos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private List<ResponseMessage> responseMessageForGET()
	{
	    return (List<ResponseMessage>) new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

		{
	        add(new ResponseMessageBuilder()
	            .code(500)
	            .message("500 message")
	            .responseModel(new ModelRef("Error"))
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(403)
	            .message("Forbidden!")
	            .build());
	    }};
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.restful.gestaodepedidos.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo())
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET())
				.securitySchemes(Arrays.asList(new ApiKey("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodWdvQGdtYWlsLmNvbSIsImV4cCI6MTYxMzg0NDc5Niwicm9sZSI6WyJST0xFX0FETUlOSVNUUkFUT1IiXX0.EJ9wOQ88U0PBuGOm4vll7RUueiHwTlFSlQer4ofKc1ijZ3h29ef4IXLDd1Z3rNZhMiXFWyE-cDVqGIR2wrpZLw", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
				.securityContexts(Arrays.asList(securityContext()));
	}
	
	private ApiInfo metaInfo() {
		return new ApiInfoBuilder()
					.title("API de Gestão de Pedidos")
					.description("API de cadastro e consulta de pedidos com recursos de autorização de usuário")
					.version("1.0.0")
					.termsOfServiceUrl("http://localhost:8080")
					.license("Apache License 2.0")
					.licenseUrl("https://www.apache.org/licesen.html")
					.build();
	}
	
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(defaultAuth())
	        .forPaths(PathSelectors.ant("/users/**"))
	        .build();
	}

	List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope
	        = new AuthorizationScope("hugo@gmail.com", "Dearer");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Arrays.asList(
	        new SecurityReference("ADMINISTRATOR",
	        		
	        		authorizationScopes));
	}
}
