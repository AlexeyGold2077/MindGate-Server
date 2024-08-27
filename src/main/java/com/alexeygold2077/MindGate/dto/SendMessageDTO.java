package com.alexeygold2077.MindGate.dto;

public record SendMessageDTO(
        String message,
        int prompt_tokens,
        int completion_tokens,
        int total_tokens
) {}