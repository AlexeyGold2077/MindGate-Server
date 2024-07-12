package com.alexeygold2077;

import java.io.IOException;

import com.alexeygold2077.api.Proxyapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

    @Autowired Proxyapi ai;
    @Autowired Users users;

    @GetMapping("/new/user")
    public ResponseEntity<?> registerNewUser(@RequestParam(value = "id") String id,
                                             @RequestParam(value = "model") String model) {
        return switch (model) {
            case "gpt-4" -> new ResponseEntity<>(users.addUser(id, Proxyapi.OpenAIModels.GPT4), HttpStatus.OK);
            case "gpt-4o" -> new ResponseEntity<>(users.addUser(id, Proxyapi.OpenAIModels.GPT4O), HttpStatus.OK);
            case "gpt-4-turbo" ->
                    new ResponseEntity<>(users.addUser(id, Proxyapi.OpenAIModels.GPT4TURBO), HttpStatus.OK);
            default -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
        };
    }

    @GetMapping("/new/message/user")
    public ResponseEntity<?> sendmessageAsUser(@RequestParam(value = "id") String id,
                                               @RequestParam(value = "message") String message) throws IOException {
        try {
            return new ResponseEntity<>(
                    ai.getChatCompletionAsUser(
                            message,
                            users.getUser(id).getMessages(),
                            users.getUser(id).model
                    ),
                    HttpStatus.OK
            );
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
