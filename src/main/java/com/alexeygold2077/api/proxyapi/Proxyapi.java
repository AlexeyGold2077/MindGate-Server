package com.alexeygold2077.api.proxyapi;

import com.alexeygold2077.api.proxyapi.DTO.RequestData;
import com.alexeygold2077.api.proxyapi.DTO.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Proxyapi {

    private final String PROXY_API_KEY;
    private String MODEL;

    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";
    private final String ANTHROPIC_URL = "https://api.proxyapi.ru/anthropic/v1/messages";

    private RequestData requestData;

    public Proxyapi(String PROXY_API_KEY, String MODEL) {
        this.PROXY_API_KEY = PROXY_API_KEY;
        this.MODEL = MODEL;
        this.requestData = new RequestData(this.MODEL, new LinkedList<>());
        requestData.addMessage("system", "You are a helpful assistant.");
    }

    public String sendMessage(String role, String message) throws IOException {
        return parseResponseToMessage(request(role, message));
    }

    private String parseResponseToMessage(String response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseData responseData = objectMapper.readValue(response, ResponseData.class);

        return responseData.getChoices().get(0).getMessage().getContent();
    }

    private String request(String role, String message) throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        requestData.addMessage(role, message);

        okhttp3.Request request = null;

        String jsonString = objectMapper.writeValueAsString(this.requestData);
        request = new okhttp3.Request.Builder()
                .url(OPENAI_URL)
                .post(RequestBody.create(jsonString, MediaType.get("application/json; charset=utf-8")))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + PROXY_API_KEY)
                .build();

        return client.newCall(request).execute().body().string();
    }
}
