package com.alexeygold2077.MindGate.dto;

public class SendMessageDTO extends BaseDTO {

    private final String response_message;
    private final int spent_words;

    public SendMessageDTO(String response_message, int spent_words, StatusCode status_code) {
        super(status_code);
        this.response_message = response_message;
        this.spent_words = spent_words;
    }

    public String getResponse_message() {
        return response_message;
    }

    public int getSpent_words() {
        return spent_words;
    }
}
