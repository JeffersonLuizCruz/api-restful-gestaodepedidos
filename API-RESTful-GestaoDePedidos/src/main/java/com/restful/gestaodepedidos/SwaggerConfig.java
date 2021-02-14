package com.restful.gestaodepedidos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		return new ApiInfoBuilder()
					.title("API de Gestão de Pedidos")
					.description("API de cadastro e consulta de pedidos com recursos de autorização de usuário")
					.termsOfServiceUrl("http://localhost:8080")
					.license("Apache License 2.0")
					.licenseUrl("https://www.apache.org/licesen.html")
					.version("1.0")
					.build();
	}
}
