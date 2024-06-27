package com.alexeygold2077.api.proxyapi;

import com.alexeygold2077.api.proxyapi.DTO.RequestData;
import com.alexeygold2077.api.proxyapi.DTO.ResponseDefault;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedList;
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
        requestData.addMessage(role, message);
        return parseResponseToMessage(request());
    }

    private String parseResponseToMessage(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDefault responseDefault = objectMapper.readValue(response, ResponseDefault.class);
        return responseDefault.getChoices().get(0).getMessage().getContent();
    }

    private String request() throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(OPENAI_URL)
                .post(RequestBody.create(objectMapper.writeValueAsString(requestData),
                        MediaType.get("application/json; charset=utf-8")))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + PROXY_API_KEY)
                .build();

        return client.newCall(request).execute().body().string();
    }
}
