package com.alexeygold2077.MindGate;

import com.alexeygold2077.MindGate.dto.*;
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
    public BaseDTO clearDialogue(@RequestParam String id) {
        proxyapi.clearDialogue(id);
        return new BaseDTO(StatusCode.SUCCESS);
    }

    @GetMapping("/getMessages")
    public SimpleDTO<LinkedList<Message>> getMessages(@RequestParam String id) {
        return new SimpleDTO<>(proxyapi.getMessages(id), StatusCode.SUCCESS);
    }

    @GetMapping("/getModel")
    public SimpleDTO<String> getModel(@RequestParam String id) {
        return new SimpleDTO<>(proxyapi.getModel(id), StatusCode.SUCCESS);
    }

    @PatchMapping("/setModel")
    public BaseDTO setModel(@RequestParam String id, @RequestParam String model) {
        proxyapi.setModel(id, model);
        return new BaseDTO(StatusCode.SUCCESS);
    }

    @GetMapping("/getBalance")
    public SimpleDTO<Integer> getBalance(@RequestParam String id) {
        return new SimpleDTO<>(proxyapi.getBalance(id), StatusCode.SUCCESS);
    }

    @PatchMapping("/addBalance")
    public BaseDTO getBalance(@RequestParam String id, @RequestParam Integer amount) {
        proxyapi.addBalance(id, amount);
        return new BaseDTO(StatusCode.SUCCESS);
    }
}