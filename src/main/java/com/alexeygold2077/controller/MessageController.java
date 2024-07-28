package com.alexeygold2077.controller;

import com.alexeygold2077.model.Proxyapi;
import com.alexeygold2077.repository.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    Proxyapi ai;
    @Autowired
    UserBase userBase;

    @PostMapping("/{userId}")
    public ResponseEntity<String> newMessageAsUser(@PathVariable Long userId,
                                                   @RequestParam Proxyapi.Roles role,
                                                   @RequestParam String message) throws IOException {
        try {
            return new ResponseEntity<>(
                    ai.getChatCompletionMessageAs(
                            message,
                            userBase.getUser(userId).getMessages(),
                            userBase.getUser(userId).model,
                            role
                    ),
                    HttpStatus.OK
            );
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserMessages(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(userBase.getUser(userId).messages, HttpStatus.OK);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
