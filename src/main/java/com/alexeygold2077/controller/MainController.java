package com.alexeygold2077.controller;

import com.alexeygold2077.model.Proxyapi;
import com.alexeygold2077.repository.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {

    @Autowired Proxyapi ai;
    @Autowired
    UserBase userBase;

    @GetMapping("/new/user")
    public ResponseEntity<?> newUser(@RequestParam Long userId,
                                     @RequestParam(
                                             required = false,
                                             defaultValue = "DEFAULT") Proxyapi.OpenAIModels model) {
        return new ResponseEntity<>(userBase.addUser(userId, model), HttpStatus.OK);
    }

    @GetMapping("/new/message/user")
    public ResponseEntity<?> newMessageAsUser(@RequestParam(value = "userId") Long userId,
                                              @RequestParam(value = "message") String message) throws IOException {
        try {
            return new ResponseEntity<>(
                    ai.getChatCompletionMessageAsUser(
                            message,
                            userBase.getUser(userId).getMessages(),
                            userBase.getUser(userId).model
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
                            userBase.getUser(userId).getMessages(),
                            userBase.getUser(userId).model
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
            return new ResponseEntity<>(userBase.getUser(userId).messages, HttpStatus.OK);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}