package com.alexeygold2077.proxyapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class Proxyapi {

    private final String PROXY_API_KEY = "sk-YLhidrIF1tbb6UKEizF4tIW1nTKY1yGK";
    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";

    public void request() throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        RequestData requestData = new RequestData(
                "gpt-4o",
                new RequestData.Message[] {
                        new RequestData.Message("user", "Что я написал выше?")
                }
        );
        String jsonRequestData = objectMapper.writeValueAsString(requestData);
        RequestBody requestBody = RequestBody.create(jsonRequestData, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(OPENAI_URL)
                .post(requestBody)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + PROXY_API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(request);

        System.out.println("Response Code: " + response.code());
        System.out.println(response.body().string());
    }

    static class RequestData {
        private String model;
        private Message[] messages;

        public RequestData(String model, Message[] messages) {
            this.model = model;
            this.messages = messages;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Message[] getMessages() {
            return messages;
        }

        public void setMessages(Message[] messages) {
            this.messages = messages;
        }

        public static class Message {
            private String role;
            private String content;

            public Message(String role, String content) {
                this.role = role;
                this.content = content;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
