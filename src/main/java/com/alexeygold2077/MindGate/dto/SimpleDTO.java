package com.alexeygold2077.MindGate.dto;

public class SimpleDTO<T> extends BaseDTO {

    private final T data;

    public SimpleDTO(T data, StatusCode status_code) {
        super(status_code);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
