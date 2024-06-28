package com.alexeygold2077.api.proxyapi.DTO;

public record Choice(String index,
                     Message message,
                     String logprobs,
                     String finish_reason) {}