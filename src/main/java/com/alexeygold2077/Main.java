package com.alexeygold2077;

import com.alexeygold2077.api.Proxyapi;

import java.io.IOException;

public class Main {

    public static Proxyapi proxyapi;

    public static void main(String[] args) throws IOException {
        proxyapi = new Proxyapi(Private.PROXYAPI_TOKEN, Proxyapi.OpenAIModels.GPT35TURBO0613);
        System.out.println(proxyapi.sendMessage(Proxyapi.Roles.USER, "Hi"));
    }
}
