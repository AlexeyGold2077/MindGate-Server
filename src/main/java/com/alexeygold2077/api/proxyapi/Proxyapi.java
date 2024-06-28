package com.alexeygold2077.api.proxyapi;

import com.alexeygold2077.api.proxyapi.DTO.RequestData;
import com.alexeygold2077.api.proxyapi.DTO.ResponseDefault;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Proxyapi {

    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";
    private final String ANTHROPIC_URL = "https://api.proxyapi.ru/anthropic/v1/messages";

    private final String PROXY_API_KEY;
    private final String MODEL;

    private final RequestData requestData;

    public Proxyapi(String PROXY_API_KEY, String MODEL) {
        this.PROXY_API_KEY = PROXY_API_KEY;
        this.MODEL = MODEL;
        this.requestData = new RequestData(this.MODEL, new LinkedList<>());
        requestData.addMessage("system", "You answer questions briefly but offer to explain in more detail.");
    }

    public String sendMessageAsUser(String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        requestData.addMessage("user", message);
        ResponseDefault responseDefault = objectMapper.readValue(request(), ResponseDefault.class);
        return responseDefault.choices().get(0).message().content();
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
        return Objects.requireNonNull(client.newCall(request).execute().body()).string();
    }
}
