package com.alexeygold2077;

import com.alexeygold2077.api.proxyapi.Proxyapi;
import com.alexeygold2077.api.telegram.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class Main {

    public static Proxyapi proxyapi;
    public static TelegramBotsApi telegramBotsApi;

    public static void main(String[] args) throws IOException, TelegramApiException {
        proxyapi = new Proxyapi(Private.PA_TOKEN, "gpt-4o");
        telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new Bot());
    }
}
