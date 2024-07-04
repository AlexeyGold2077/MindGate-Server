package com.alexeygold2077.api.DTO;

import java.util.List;

public record ChatCompletionRequest(String model,
                                    List<Message> messages) {

    public void addMessage(String role, String message) {
        messages.add(
                new Message(role, message)
        );
    }

    public record Message(String role,
                          String content) {}
}
