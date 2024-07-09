package com.alexeygold2077;

import java.io.IOException;

import com.alexeygold2077.api.Proxyapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

    @Autowired
    private Proxyapi ai;

    @GetMapping("/sendmessage")
    public String greeting(@RequestParam(value = "message") String message) throws IOException {
        return ai.sendMessage(Proxyapi.Roles.USER, message);
    }
}
