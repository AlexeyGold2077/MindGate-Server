package com.alexeygold2077.api.proxyapi.DTO;

import java.util.List;

public record ResponseDefault(String id,
                              String object,
                              String created,
                              String model,
                              List<Choice> choices,
                              Usage usage,
                              String system_fingerprint) {

    public record Choice(String index,
                         Message message,
                         String logprobs,
                         String finish_reason) {

        public record Message(String role,
                              String content) {}
    }
    public record Usage(String prompt_tokens,
                        String completion_tokens,
                        String total_tokens) {}
}
