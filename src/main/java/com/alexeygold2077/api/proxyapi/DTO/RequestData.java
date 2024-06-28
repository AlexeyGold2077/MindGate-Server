package com.alexeygold2077.api.proxyapi.DTO;

import java.util.List;

public record RequestData(String model,
                          List<Message> messages) {

    public void addMessage(String role, String message) {
        messages.add(
                new Message(role, message)
        );
    }

    public record Message(String role,
                   String content) {}
}
