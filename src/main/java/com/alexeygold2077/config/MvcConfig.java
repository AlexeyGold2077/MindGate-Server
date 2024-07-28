package com.alexeygold2077.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.alexeygold2077")
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer { }