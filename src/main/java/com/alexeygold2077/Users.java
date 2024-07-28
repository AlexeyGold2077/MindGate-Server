package com.alexeygold2077;

import com.alexeygold2077.proxyapi.DTO.ChatCompletionRequest;
import com.alexeygold2077.proxyapi.Proxyapi;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class Users {

    private final Map<Long, User> usersArray = new HashMap<>();

    public User addUser(Long id, Proxyapi.OpenAIModels model) {
        User newUser = new User(id, model);
        usersArray.put(id, newUser);
        return newUser;
    }

    public User getUser(Long id) {
        return usersArray.get(id);
    }

    public static class User {

        public Long id;
        public Proxyapi.OpenAIModels model;
        public List<ChatCompletionRequest.Message> messages;

        public List<ChatCompletionRequest.Message> getMessages() {
            return messages;
        }

        User(Long id, Proxyapi.OpenAIModels model) {
            this.id = id;
            this.model = model;
            this.messages = new LinkedList<>();
        }
    }
}
