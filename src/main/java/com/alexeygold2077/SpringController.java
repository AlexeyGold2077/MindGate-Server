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
    public ResponseEntity<?> registerNewUser(@RequestParam(value = "id") Long id,
                                             @RequestParam(value = "model") Proxyapi.OpenAIModels model) {
        return new ResponseEntity<>(users.addUser(id, model), HttpStatus.OK);
    }

    @GetMapping("/new/message/user")
    public ResponseEntity<?> sendmessageAsUser(@RequestParam(value = "id") Long id,
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
