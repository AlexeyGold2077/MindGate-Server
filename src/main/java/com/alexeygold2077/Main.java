package com.alexeygold2077;

import com.alexeygold2077.api.Proxyapi;

import java.io.IOException;

public class Main {

    public static Proxyapi proxyapi;

    public static void main(String[] args) throws IOException {
        proxyapi = new Proxyapi(Private.PROXYAPI_TOKEN, "gpt-4o");
        System.out.println(proxyapi.sendMessageAsSystem("You are agressive!"));
        System.out.println(proxyapi.sendMessageAsUser("Hello!"));
    }
}
