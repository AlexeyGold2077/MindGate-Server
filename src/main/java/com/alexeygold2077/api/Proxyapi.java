package com.alexeygold2077.api;

import com.alexeygold2077.api.DTO.Dialogue;
import com.alexeygold2077.api.DTO.DefaultResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Proxyapi {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final Dialogue dialogue;

    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";
    private final String ANTHROPIC_URL = "https://api.proxyapi.ru/anthropic/v1/messages";

    private final String PROXY_API_KEY;
    private final OpenAIModels MODEL;

    public Proxyapi(String PROXY_API_KEY, OpenAIModels MODEL) {
        this.PROXY_API_KEY = PROXY_API_KEY;
        this.MODEL = MODEL;
        this.okHttpClient = new OkHttpClient.Builder().readTimeout(100, TimeUnit.SECONDS).build();
        this.objectMapper = new ObjectMapper();
        this.dialogue = new Dialogue(this.MODEL.getName(), new LinkedList<>());
    }

    public String sendMessageAsUser(String message) throws IOException {
        return sendMessage("user", message);
    }
    public String sendMessageAsSystem(String message) throws IOException {
        return sendMessage("system", message);
    }

    private String sendMessage(String role, String message) throws IOException {
        dialogue.addMessage(role, message);
        DefaultResponse defaultResponse = objectMapper.readValue(messageRequest(), DefaultResponse.class);
        return defaultResponse.choices().get(0).message().content();
    }

    private String messageRequest() throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String jsonRequest = objectMapper.writeValueAsString(dialogue);
        RequestBody requestBody = RequestBody.create(jsonRequest, JSON);
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
        } catch (IOException ioe) {
            System.out.println("ERROR: " + ioe);
        }
        return responseBody;
    }

    public enum OpenAIModels {
        GPT4O("gpt-4o"),
        GPT4TURBO("gpt-4-turbo"),
        GPT35TURBO0125("gpt-3.5-turbo-0125"),
        GPT35TURBO0613("gpt-3.5-turbo-0613"),
        TEXTEMBEDDING3SMALL("text-embedding-3-small");
        private final String name;
            OpenAIModels(String code){
                this.name = code;
        }
        public String getName() { return name; }
    }
}
