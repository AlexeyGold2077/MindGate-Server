package com.alexeygold2077;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.alexeygold2077")
public class ProxyapiJavaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProxyapiJavaApplication.class, args);
	}
}
