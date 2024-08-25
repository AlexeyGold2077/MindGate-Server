package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    Proxyapi proxyapi;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String id, @RequestParam String message, @RequestParam String role) throws IOException {
        return proxyapi.sendMessage(id, message, role);
    }

    @PatchMapping("/clearMessages")
    public String clearDialogue(@RequestParam String id) {
        proxyapi.clearDialogue(id);
        return "200";
    }

    @GetMapping("/getMessages")
    public LinkedList<Message> getMessages(@RequestParam String id) {
        return proxyapi.getMessages(id);
    }

    @GetMapping("/getModel")
    public String getModel(@RequestParam String id) {
        return proxyapi.getModel(id);
    }

    @PatchMapping("/setModel")
    public String setModel(@RequestParam String id, @RequestParam String model) {
        proxyapi.setModel(id, model);
        return "200";
    }
}