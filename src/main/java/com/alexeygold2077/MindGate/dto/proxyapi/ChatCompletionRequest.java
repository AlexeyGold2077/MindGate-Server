package com.alexeygold2077.MindGate.dto.proxyapi;

import java.util.List;

public record ChatCompletionRequest(
        String model,
        List<Message> messages
) {}