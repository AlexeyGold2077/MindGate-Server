package com.alexeygold2077;

import com.alexeygold2077.api.proxyapi.Proxyapi;
import com.alexeygold2077.api.telegram.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Proxyapi proxyapi = new Proxyapi("sk-YLhidrIF1tbb6UKEizF4tIW1nTKY1yGK", "gpt-4o");

    public static void main(String[] args) throws IOException, TelegramApiException {
        /*Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Введите запрос: ");
            System.out.println(proxyapi.sendMessageAsUser(in.nextLine()));
        }*/

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new Bot());
    }
}
