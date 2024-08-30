package com.alexeygold2077.MindGate.dto;

public enum StatusCode {
    SUCCESS("SUCCESS"),
    INSUFFICIENT_BALANCE("INSUFFICIENT BALANCE");

    private final String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
