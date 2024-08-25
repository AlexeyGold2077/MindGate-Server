package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.Message;

import java.util.LinkedList;

public class User {

    private String model;
    private LinkedList<Message> messages;

    public User() {
        this.model = "gpt-4o";
        this.messages = new LinkedList<>();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }
}
