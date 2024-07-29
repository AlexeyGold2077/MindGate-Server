package com.alexeygold2077.MindGate.repository;

import com.alexeygold2077.MindGate.model.dto.proxyapi.ChatCompletionRequestDTO;
import com.alexeygold2077.MindGate.model.dto.proxyapi.ChatCompletionResultDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    public String getChatCompletionMessageAs(String content,
                                             List<ChatCompletionRequestDTO.Message> messages,
                                             OpenAIModels model,
                                             Roles role) throws IOException {
        if (Objects.equals(role.getName(), "user"))
            return getChatCompletionMessage(content, messages, Roles.USER, model);
        if (Objects.equals(role.getName(), "system"))
            return getChatCompletionMessage(content, messages, Roles.SYSTEM, model);
        throw new IOException();
    }

    public String getChatCompletionMessageAsUser(String content,
                                                 List<ChatCompletionRequestDTO.Message> messages,
                                                 OpenAIModels model) throws IOException {
        return getChatCompletionMessage(content, messages, Roles.USER, model);
    }

    public String getChatCompletionMessageAsSystem(String content,
                                                   List<ChatCompletionRequestDTO.Message> messages,
                                                   OpenAIModels model) throws IOException {
        return getChatCompletionMessage(content, messages, Roles.SYSTEM, model);
    }

    private String getChatCompletionMessage(String content,
                                            List<ChatCompletionRequestDTO.Message> messages,
                                            Roles role,
                                            OpenAIModels model) throws IOException {

        ChatCompletionResultDTO chatCompletionResultDTO = getChatCompletion(content, messages, role, model);
        return chatCompletionResultDTO.choices().get(0).message().content();
    }

    public ChatCompletionResultDTO getChatCompletion(String content,
                                                     List<ChatCompletionRequestDTO.Message> messages,
                                                     Roles role,
                                                     OpenAIModels model) throws IOException {

        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        ChatCompletionRequestDTO chatCompletionRequestDTO =
                new ChatCompletionRequestDTO(messages, model.getName());
        chatCompletionRequestDTO.addMessage(content, role.getName(), "name1");

        String jsonBody = objectMapper.writeValueAsString(chatCompletionRequestDTO);

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

        return objectMapper.readValue(responseBody, ChatCompletionResultDTO.class);
    }

    public enum Roles {
        USER("user"),
        SYSTEM("system");
        private final String name;

        Roles(String code) {
            this.name = code;
        }

        public String getName() {
            return name;
        }
    }

    public enum OpenAIModels {
        DEFAULT("gpt-4"),
        GPT4("gpt-4"),
        GPT4O("gpt-4o"),
        GPT4TURBO("gpt-4-turbo");
        private final String name;

        OpenAIModels(String code) {
            this.name = code;
        }

        public String getName() {
            return name;
        }
    }
}