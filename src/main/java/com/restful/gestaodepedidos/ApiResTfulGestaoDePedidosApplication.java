package com.restful.gestaodepedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiResTfulGestaoDePedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiResTfulGestaoDePedidosApplication.class, args);
	}

}
