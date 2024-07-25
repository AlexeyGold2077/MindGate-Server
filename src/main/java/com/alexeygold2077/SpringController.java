package com.alexeygold2077;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SpringController {

    @GetMapping("/hello-world")
    public String getHelloWorld() {
        return "hello_world";
    }

}
