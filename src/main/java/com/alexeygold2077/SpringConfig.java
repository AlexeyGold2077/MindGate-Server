package com.alexeygold2077;

import com.alexeygold2077.api.Proxyapi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application-dev.properties")
public class SpringConfig {

    @Value("${PROXY_API_KEY}")
    private String PROXY_API_KEY;

    @Bean
    public Proxyapi proxyapi() {
        return new Proxyapi(PROXY_API_KEY, Proxyapi.OpenAIModels.GPT4O);
    }
}
