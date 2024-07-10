package com.alexeygold2077.api;

import com.alexeygold2077.api.DTO.ChatCompletionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Proxyapi {

    @Autowired
    private OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";
    private final String ANTHROPIC_URL = "https://api.proxyapi.ru/anthropic/v1/messages";

    private final String PROXY_API_KEY;
    private final OpenAIModels MODEL;

    public Proxyapi(String PROXY_API_KEY, OpenAIModels MODEL) {
        this.PROXY_API_KEY = PROXY_API_KEY;
        this.MODEL = MODEL;
        this.objectMapper = new ObjectMapper();
    }

    public String chatCompletionRequest(ChatCompletionRequest chatCompletionRequest) throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String jsonBody = objectMapper.writeValueAsString(chatCompletionRequest);
        RequestBody requestBody = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(OPENAI_URL)
                .header("Authorization", "Bearer " + PROXY_API_KEY)
                .post(requestBody)
                .build();
        String responseBody = null;
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("ERROR: " + response.code() + " " + response.message());
            responseBody = response.body().string();
        } catch (NullPointerException npe) {
            throw new NullPointerException("ERROR: in messageRequest()");
        }
        return responseBody;
    }

    public enum Roles {
        USER("user"),
        SYSTEM("system");
        private final String name;
        Roles(String code) { this.name = code; }
        public String getName() { return name; }
    }

    public enum OpenAIModels {
        GPT4("gpt-4"),
        GPT4O("gpt-4o"),
        GPT4TURBO("gpt-4-turbo");
        private final String name;
        OpenAIModels(String code) { this.name = code; }
        public String getName() { return name; }
    }
}
