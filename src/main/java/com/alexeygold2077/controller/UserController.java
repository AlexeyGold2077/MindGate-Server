package com.alexeygold2077.controller;

import com.alexeygold2077.model.Proxyapi;
import com.alexeygold2077.repository.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserBase userBase;

    @PostMapping("/new/{userId}")
    public ResponseEntity<UserBase.User> newUser(@PathVariable Long userId,
                                                 @RequestParam(required = false, defaultValue = "DEFAULT")
                                     Proxyapi.OpenAIModels model) {
        return new ResponseEntity<>(userBase.addUser(userId, model), HttpStatus.OK);
    }

}
