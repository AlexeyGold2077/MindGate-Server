package com.alexeygold2077;

import java.io.IOException;

import com.alexeygold2077.api.DTO.ChatCompletionRequest;
import com.alexeygold2077.api.Proxyapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

    @Autowired
    private Proxyapi ai;

    @GetMapping("/chatCompletionRequest")
    public String greeting(@RequestBody ChatCompletionRequest chatCompletionRequest) throws IOException {
        return ai.chatCompletionRequest(chatCompletionRequest);
    }
}
