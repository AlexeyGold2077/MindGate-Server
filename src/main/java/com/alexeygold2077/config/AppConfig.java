package com.alexeygold2077.config;

import com.alexeygold2077.model.Proxyapi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-private.properties")
public class AppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Value("${PROXY_API_KEY}")
    private String PROXY_API_KEY;

    @Bean
    public Proxyapi proxyapi() {
        return new Proxyapi(PROXY_API_KEY);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().readTimeout(100, TimeUnit.SECONDS).build();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { MvcConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}