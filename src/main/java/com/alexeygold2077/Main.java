package com.alexeygold2077;

import com.alexeygold2077.telegram.Bot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        try {
            TelegramBotsApi telegramBotsApi = context.getBean(TelegramBotsApi.class);
            telegramBotsApi.registerBot(context.getBean(Bot.class));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}