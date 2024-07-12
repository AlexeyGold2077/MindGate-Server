package com.alexeygold2077;

import com.alexeygold2077.api.DTO.ChatCompletionRequest;
import com.alexeygold2077.api.DTO.ChatCompletionResult;
import com.alexeygold2077.api.Proxyapi;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class Users {

    private final Map<String, User> usersArray = new HashMap<>();
    private User lastUser;

    public User addUser(String id, Proxyapi.OpenAIModels model) {
        lastUser = new User(id, model);
        usersArray.put(id, lastUser);
        return lastUser;
    }

    public User getUser(String id) {
        return usersArray.get(id);
    }

    public User getLastUser() {
        return lastUser;
    }

    public static class User {

        public String id;
        public Proxyapi.OpenAIModels model;
        public List<ChatCompletionRequest.Message> messages;

        public List<ChatCompletionRequest.Message> getMessages() {
            return messages;
        }

        User(String id, Proxyapi.OpenAIModels model) {
            this.id = id;
            this.model = model;
            this.messages =
                    new LinkedList<ChatCompletionRequest.Message>();
        }
    }
}
