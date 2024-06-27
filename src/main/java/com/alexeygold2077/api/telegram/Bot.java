/*
package com.alexeygold2077.api.telegram;

import com.alexeygold2077.Main;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

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
                Message message = update.getMessage();
                String chatId = message.getChatId().toString();

                String response = parseMessage(message.getText());

                SendMessage outMess = new SendMessage();

                outMess.setChatId(chatId);
                outMess.setText(response);

                execute(outMess);
            }
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(String textMsg) throws IOException {
        if (textMsg.equals("/start"))
            return "Стартуем!";
        else
            return Main.proxyapi.request(textMsg);
    }
}
*/
