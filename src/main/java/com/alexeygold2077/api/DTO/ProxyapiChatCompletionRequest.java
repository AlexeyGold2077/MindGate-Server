package com.alexeygold2077.api.DTO;

import java.util.List;

public record ProxyapiChatCompletionRequest(List<Message> messages,
                                            String model) {

    public void addMessage(String message, String role, String name) {
        messages.add(new Message(role, message, name));
    }

    public record Message(String content,
                          String role,
                          String name) {}
}
