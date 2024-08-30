package com.alexeygold2077.MindGate.dto;

public enum StatusCode {
    SUCCESS("SUCCESS");

    private final String status_str;

    StatusCode(String status_str) {
        this.status_str = status_str;
    }

    public String getStatus_str() {
        return status_str;
    }
}
