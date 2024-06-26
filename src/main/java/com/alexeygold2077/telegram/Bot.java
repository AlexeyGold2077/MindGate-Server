package com.alexeygold2077.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private static final String BOT_NAME = "AiBot456_bot";
    private static final String BOT_TOKEN = "7477349748:AAG9KU6hz7_YhuxXYCwDsozQYOOrESLWnKY";

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMess = update.getMessage();
                String chatId = inMess.getChatId().toString();

                String response = parseMessage(inMess.getText());

                SendMessage outMess = new SendMessage();

                outMess.setChatId(chatId);
                outMess.setText(response);

                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(String textMsg) {
        if (textMsg.equals("/start"))
            return "Приветствую, бот знает много цитат. Жми /get, чтобы получить случайную из них";
        if (textMsg.equals("/get"))
            return "иди нахуй или умри!";
        else
            return "Сообщение не распознано";
    }
}
