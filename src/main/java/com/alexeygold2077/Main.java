package com.alexeygold2077;

import com.alexeygold2077.proxyapi.Proxyapi;
import com.alexeygold2077.telegram.Bot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        /*ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        try {
            TelegramBotsApi telegramBotsApi = context.getBean(TelegramBotsApi.class);
            telegramBotsApi.registerBot(context.getBean(Bot.class));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/

        Proxyapi proxyapi = new Proxyapi();

        proxyapi.request();
    }
}