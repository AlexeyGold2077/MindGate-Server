package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.ChatCompletionRequest;
import com.alexeygold2077.MindGate.dto.ChatCompletionResponse;
import com.alexeygold2077.MindGate.dto.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Component
public class Proxyapi {

    private final String PROXY_API_KEY;

    private final String OPENAI_URL = "https://api.proxyapi.ru/openai/v1/chat/completions";
    private final String GPT4TURBO = "gpt-4-turbo";

    private final Map<String, LinkedList<Message>> users;

    Proxyapi(String PROXY_API_KEY) {
        this.PROXY_API_KEY = PROXY_API_KEY;
        this.users = new HashMap<>();
    }

    public String sendMessageAsUser(String id, String message, String role) throws IOException {

        LinkedList<Message> user = validateUser(id);

        user.add(new Message(role, message));

        ChatCompletionRequest request = new ChatCompletionRequest(GPT4TURBO, user);

        ChatCompletionResponse response = getChatCompletion(request);

        String responseMessage = response.choices().get(0).message().content();

        user.add(new Message("assistant", responseMessage));

        return responseMessage;
    }

    public void clearDialogue(String id) {

        users.put(id, new LinkedList<>());
    }

    private ChatCompletionResponse getChatCompletion(ChatCompletionRequest request) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + PROXY_API_KEY);

        HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(request), headers);

        String response = restTemplate.exchange(OPENAI_URL, HttpMethod.POST, httpEntity, String.class).getBody();

        return objectMapper.readValue(response, ChatCompletionResponse.class);
    }

    private LinkedList<Message> validateUser(String id) {

        if (!users.containsKey(id))
            users.put(id, new LinkedList<>());

        return users.get(id);
    }
}