package com.alexeygold2077.MindGate.dto;

public record SendMessageDTO(
        String message,
        int spent_tokens,
        int userBalance
) {}