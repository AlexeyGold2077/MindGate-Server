package com.alexeygold2077.MindGate.dto;

public class BaseDTO {

    private final StatusCode status_code;

    public BaseDTO(StatusCode status_code) {
        this.status_code = status_code;
    }

    public StatusCode getStatus_code() {
        return status_code;
    }
}
