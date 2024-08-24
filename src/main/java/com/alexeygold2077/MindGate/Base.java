package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.proxyapi.ChatCompletionRequest;
import com.alexeygold2077.MindGate.dto.proxyapi.Message;
import com.alexeygold2077.MindGate.util.HashUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Component
public class Base {

    private final Map<String, User> users;

    public Base() {
        this.users = new HashMap<>();
    }

    public String addUser() {
        String newUserId = generateId();
        System.out.println(newUserId);
        users.put(newUserId, new User());
        return newUserId;
    }

    public boolean removeUser(String id) {
        return users.remove(id) != null;
    }

    public User getUser(String id) {
        return users.get(id);
    }

    private String generateId() {
        return HashUtils.SHA256(String.valueOf(System.currentTimeMillis())).substring(0, 20);
    }

    @JsonAutoDetect
    public static class User {

        private final ChatCompletionRequest request;

        User() {
            this.request = new ChatCompletionRequest(
                    "gpt-4o",
                    new LinkedList<Message>()
            );
        }

        public ChatCompletionRequest getRequest() {
            return this.request;
        }
//
//        public void addMessageAsUser(String content) {
//            request.addMessage(content, Proxyapi.Role.USER, "1");
//        }
//
//        public void addMessageAsSystem(String content) {
//            request.addMessage(content, Proxyapi.Role.SYSTEM, "1");
//        }
    }
}