package com.alexeygold2077.MindGate.dto;

import com.alexeygold2077.MindGate.dto.proxyapi.ChatCompletionRequest;

public record User(String id,
                   ChatCompletionRequest request) {
}
