package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.proxyapi.ChatCompletionRequest;
import com.alexeygold2077.MindGate.dto.proxyapi.ChatCompletionResponse;
import com.alexeygold2077.MindGate.dto.proxyapi.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


import java.io.IOException;
import java.util.LinkedList;

@Component
public class Proxyapi {

    private static final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";

    public static String sendMessage(LinkedList<Message> messages) throws IOException {

        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("gpt-4-turbo", messages);

        ChatCompletionResponse chatCompletionResponse = getChatCompletion(chatCompletionRequest);

        messages.add(new Message("assistant", ));

        return chatCompletionResponse.choices().get(0).message().content();
    }

    public static ChatCompletionResponse getChatCompletion(ChatCompletionRequest request) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer sk-a37lMOhrS97evDE445rwUC30XivvwQS5");

        HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(request), headers);

        System.out.println(httpEntity);

        String response = restTemplate.exchange(OPENAI_URL, HttpMethod.POST, httpEntity, String.class).getBody();

        System.out.println(response);

        return objectMapper.readValue(response, ChatCompletionResponse.class);
    }
}