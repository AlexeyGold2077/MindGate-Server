package com.alexeygold2077.api;

import com.alexeygold2077.api.DTO.ChatCompletionRequest;
import com.alexeygold2077.api.DTO.ChatCompletionResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@Component
public class Proxyapi {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final ChatCompletionRequest chatCompletionRequest;

    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";
    private final String ANTHROPIC_URL = "https://api.proxyapi.ru/anthropic/v1/messages";

    private final String PROXY_API_KEY;
    private final OpenAIModels MODEL;

    public Proxyapi(String PROXY_API_KEY, OpenAIModels MODEL) {
        this.PROXY_API_KEY = PROXY_API_KEY;
        this.MODEL = MODEL;
        this.okHttpClient = new OkHttpClient.Builder().readTimeout(100, TimeUnit.SECONDS).build();
        this.objectMapper = new ObjectMapper();
        this.chatCompletionRequest = new ChatCompletionRequest(new LinkedList<>(), this.MODEL.getName());
    }

    public String sendMessage(Roles role, String message) throws IOException {
        chatCompletionRequest.addMessage(role.getName(), message, "user");
        ChatCompletionResult chatCompletionResult = objectMapper.readValue(messageRequest(), ChatCompletionResult.class);
        return chatCompletionResult.choices().get(0).message().content();
    }

    private String messageRequest() throws IOException {
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
            if (!response.isSuccessful()) {
                throw new IOException("ERROR: " + response.code() + " " + response.message());
            }
            responseBody = response.body().string();
        } catch (NullPointerException npe) {
            System.out.println("ERROR: NullPointerException while making request.");
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
        GPT4O("gpt-4o"),
        GPT4TURBO("gpt-4-turbo"),
        GPT35TURBO0125("gpt-3.5-turbo-0125"),
        GPT35TURBO0613("gpt-3.5-turbo-0613"),
        TEXTEMBEDDING3SMALL("text-embedding-3-small");
        private final String name;
        OpenAIModels(String code) { this.name = code; }
        public String getName() { return name; }
    }
}
