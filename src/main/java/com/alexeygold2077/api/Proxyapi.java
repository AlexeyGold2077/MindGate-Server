package com.alexeygold2077.api;

import com.alexeygold2077.api.DTO.ChatCompletionRequest;
import com.alexeygold2077.api.DTO.ChatCompletionResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;

@Component
public class Proxyapi {

    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";
    private final String ANTHROPIC_URL = "https://api.proxyapi.ru/anthropic/v1/messages";

    private final String PROXY_API_KEY;

    @Autowired private OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public Proxyapi(String PROXY_API_KEY) {
        this.PROXY_API_KEY = PROXY_API_KEY;
        this.objectMapper = new ObjectMapper();
    }

    public String getChatCompletionAsUser(String message, OpenAIModels model) throws IOException {
        String ret = getChatCompletion(message, Roles.USER, model);
        System.out.println("user - " + message);
        System.out.println("bot - " + ret);
        return ret;
    }

    private String getChatCompletion(String message, Roles role, Proxyapi.OpenAIModels model) throws IOException {

        ChatCompletionResult chatCompletionResult =
                objectMapper.readValue(chatCompletionRequest(message, role, model), ChatCompletionResult.class);

        return chatCompletionResult.choices().get(0).message().content();
    }

    public String chatCompletionRequest(String message, Roles role, Proxyapi.OpenAIModels model) throws IOException {

        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        ChatCompletionRequest chatCompletionRequest =
                new ChatCompletionRequest(new LinkedList<>(), model.getName());
        chatCompletionRequest.addMessage(message, role.getName(), "user");

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
