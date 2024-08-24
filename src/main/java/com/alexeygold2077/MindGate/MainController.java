package com.alexeygold2077.MindGate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    Proxyapi proxyapi;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String id, @RequestParam String message) throws IOException {
        return proxyapi.sendMessageAsUser(id, message);
    }
}