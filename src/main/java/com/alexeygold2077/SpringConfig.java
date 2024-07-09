package com.alexeygold2077;

import com.alexeygold2077.api.Proxyapi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public Proxyapi proxyapi() {
        return new Proxyapi("sk-2ToZXAwaU9CMZxX4ota5gYUM9bO5k9zQ", Proxyapi.OpenAIModels.GPT4O);
    }
}
