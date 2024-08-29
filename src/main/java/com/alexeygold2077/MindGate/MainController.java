package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.DefaultDTO;
import com.alexeygold2077.MindGate.dto.DefaultDataDTO;
import com.alexeygold2077.MindGate.dto.SendMessageDTO;
import com.alexeygold2077.MindGate.dto.proxyapi.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    Proxyapi proxyapi;

    @PostMapping("/sendMessage")
    public SendMessageDTO sendMessage(@RequestParam String id, @RequestParam String message, @RequestParam String role) throws IOException {
        return proxyapi.sendMessage(id, message, role);
    }

    @PatchMapping("/clearMessages")
    public DefaultDTO<String> clearDialogue(@RequestParam String id) {
        proxyapi.clearDialogue(id);
        return new DefaultDTO<>("SUCCESS");
    }

    @GetMapping("/getMessages")
    public DefaultDataDTO<LinkedList<Message>> getMessages(@RequestParam String id) {
        return new DefaultDataDTO<>(proxyapi.getMessages(id), "SUCCESS");
    }

    @GetMapping("/getModel")
    public DefaultDataDTO<String> getModel(@RequestParam String id) {
        return new DefaultDataDTO<>(proxyapi.getModel(id), "SUCCESS");
    }

    @PatchMapping("/setModel")
    public DefaultDTO<String> setModel(@RequestParam String id, @RequestParam String model) {
        proxyapi.setModel(id, model);
        return new DefaultDTO<>("SUCCESS");
    }

    @GetMapping("/getBalance")
    public DefaultDataDTO<Integer> getBalance(@RequestParam String id) {
        return new DefaultDataDTO<>(proxyapi.getBalance(id), "SUCCESS");
    }

    @PatchMapping("/addBalance")
    public DefaultDTO<String> getBalance(@RequestParam String id, @RequestParam Integer amount) {
        proxyapi.addBalance(id, amount);
        return new DefaultDTO<>("SUCCESS");
    }
}