package com.alexeygold2077.MindGate.model.dto.proxyapi;

import java.util.List;

public record ChatCompletionRequestDTO(List<Message> messages,
                                       String model) {

    public void addMessage(String message,
                           String role,
                           String name) {
        messages.add(new Message(message, role, name));
    }

    public record Message(String content,
                          String role,
                          String name) {}
}