package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication

@OpenAPIDefinition(
info = @Info( title="Trade Card Store API", version="1.0",
		 description="API that allows a Store to keep track of invetory and sales of cards"))

public class TradingCardShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingCardShopApplication.class, args);
	}

}
