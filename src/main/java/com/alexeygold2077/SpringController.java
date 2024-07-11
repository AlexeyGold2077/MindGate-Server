package com.alexeygold2077;

import java.io.IOException;

import com.alexeygold2077.api.Proxyapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

    @Autowired
    private Proxyapi ai;

    @GetMapping("/sendmessageAsUser/gpt4")
    public String sendmessageAsUserGpt4(@RequestParam(value = "userId") String userId,
                                        @RequestParam(value = "message") String message) throws IOException {
        return ai.getChatCompletionAsUser(message, Proxyapi.OpenAIModels.GPT4);
    }

    @GetMapping("/sendmessageAsUser/gpt-4o")
    public String sendmessageAsUserGpt4o(@RequestParam(value = "userId") String userId,
                                         @RequestParam(value = "message") String message) throws IOException {
        return ai.getChatCompletionAsUser(message, Proxyapi.OpenAIModels.GPT4O);
    }

    @GetMapping("/sendmessageAsUser/gpt-4-turbo")
    public String sendmessageAsUserGpt4turbo(@RequestParam(value = "userId") String userId,
                                             @RequestParam(value = "message") String message) throws IOException {
        return ai.getChatCompletionAsUser(message, Proxyapi.OpenAIModels.GPT4TURBO);
    }
}
