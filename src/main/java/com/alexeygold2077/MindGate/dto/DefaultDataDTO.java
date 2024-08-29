package com.alexeygold2077.MindGate.dto;

public record DefaultDataDTO<T>(
        T data,
        String status_message
) {}