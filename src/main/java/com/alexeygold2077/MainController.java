package com.alexeygold2077;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alexeygold2077.proxyapi.Proxyapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired Proxyapi ai;
    @Autowired Users users;

    @GetMapping("/new/user")
    public ResponseEntity<?> newUser(@RequestParam Long userId,
                                     @RequestParam(required = false, defaultValue = "DEFAULT") Proxyapi.OpenAIModels model) {
        return new ResponseEntity<>(users.addUser(userId, model), HttpStatus.OK);
    }

    @GetMapping("/new/message/user")
    public ResponseEntity<?> newMessageAsUser(@RequestParam(value = "userId") Long userId,
                                              @RequestParam(value = "message") String message) throws IOException {
        try {
            return new ResponseEntity<>(
                    ai.getChatCompletionMessageAsUser(
                            message,
                            users.getUser(userId).getMessages(),
                            users.getUser(userId).model
                    ),
                    HttpStatus.OK
            );
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/new/message/system")
    public ResponseEntity<?> newMessageAsSystem(@RequestParam(value = "userId") Long userId,
                                                @RequestParam(value = "message") String message) throws IOException {
        try {
            return new ResponseEntity<>(
                    ai.getChatCompletionMessageAsSystem(
                            message,
                            users.getUser(userId).getMessages(),
                            users.getUser(userId).model
                    ),
                    HttpStatus.OK
            );
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/user/messages")
    public ResponseEntity<?> getUserMessages(@RequestParam(value = "userId") Long userId) {
        try {
            return new ResponseEntity<>(users.getUser(userId).messages, HttpStatus.OK);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}