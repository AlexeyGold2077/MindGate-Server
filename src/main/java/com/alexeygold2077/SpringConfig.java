package com.alexeygold2077;

import com.alexeygold2077.api.Proxyapi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("application-dev.properties")
public class SpringConfig {

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
}
