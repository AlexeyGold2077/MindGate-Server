package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    Base base;
    @Autowired
    Proxyapi proxyapi;

    @PostMapping("/user")
    public User newUser() {
        String newUserId = base.addUser();
        return new User(newUserId, base.getUser(newUserId).getRequest());
    }

    @GetMapping("/user")
    public User getUser(@RequestBody User user) {
        System.out.println(user.id());
        return new User(user.id(), base.getUser(user.id()).getRequest());

    }

    @DeleteMapping("/user")
    public Boolean removeUser(@RequestBody User user) {
        return base.removeUser(user.id());
    }

    @PostMapping("/user/message")
    public String sendMessage(@RequestBody User user, @RequestParam String content) throws IOException {
        base.getUser(user.id()).addMessageAsUser(content);
        return proxyapi.sendMessage(base.getUser(user.id()).getRequest());
    }
}