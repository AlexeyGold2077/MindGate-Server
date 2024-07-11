package com.alexeygold2077;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = "com.alexeygold2077")
public class ProxyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyapiApplication.class, args);
	}
}
