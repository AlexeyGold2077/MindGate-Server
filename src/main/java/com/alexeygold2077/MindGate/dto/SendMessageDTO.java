package com.alexeygold2077.MindGate.dto;

public record SendMessageDTO(
        String response_message,
        int spent_words,
        String status_message
) {}