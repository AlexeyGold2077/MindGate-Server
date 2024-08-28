package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.proxyapi.Message;

import java.util.LinkedList;

public class User {

    private String model;
    private LinkedList<Message> messages;
    private int balance;

    public User(String model) {
        this.model = model;
        this.messages = new LinkedList<>();
        this.balance = 0;
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

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
