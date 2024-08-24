package com.alexeygold2077.MindGate.dto;

import java.util.List;

public record ChatCompletionRequest(
        String model,
        List<Message> messages
) {}