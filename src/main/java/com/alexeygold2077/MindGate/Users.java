package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.Message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Users {

    private final Map<String, User> userMap;

    public Users() {
        this.userMap = new HashMap<>();
    }

    public void addMessage(String id, String message, String role) {

        validateUser(id).getMessages().add(new Message(role, message));
    }

    public String getModel(String id) {

        return validateUser(id).getModel();
    }

    public void setModel(String id, String model) {

        validateUser(id).setModel(model);
    }

    public LinkedList<Message> getMessages(String id) {

        return validateUser(id).getMessages();
    }

    public void clearDialogue(String id) {

        validateUser(id).setMessages(new LinkedList<>());
    }

    private User validateUser(String id) {

        if (!userMap.containsKey(id))
            userMap.put(id, new User());

        return userMap.get(id);
    }
}
