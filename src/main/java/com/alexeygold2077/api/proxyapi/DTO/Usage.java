package com.alexeygold2077.api.proxyapi.DTO;

public record Usage(String prompt_tokens,
                    String completion_tokens,
                    String total_tokens) {}