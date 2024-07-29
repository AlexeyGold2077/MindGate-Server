package com.alexeygold2077.MindGate.controller;

import com.alexeygold2077.MindGate.model.dto.mindgate.NewMessageDTO;
import com.alexeygold2077.MindGate.model.dto.mindgate.ResponseDTO;
import com.alexeygold2077.MindGate.repository.Proxyapi;
import com.alexeygold2077.MindGate.repository.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/new")
public class NewController {

    @Autowired
    UserBase userBase;
    @Autowired
    Proxyapi ai;

    @PostMapping("/user")
    public ResponseEntity<ResponseDTO> newUser(@RequestParam Long user_id) {
        userBase.addUser(user_id, Proxyapi.OpenAIModels.DEFAULT);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Success"));
    }

    @PostMapping("/message")
    public ResponseEntity<NewMessageDTO> newMessage(@RequestParam Long user_id,
                                                    @RequestParam Proxyapi.Roles role,
                                                    @RequestParam String text) throws IOException {
        if (userBase.checkUser(user_id)) {
            return ResponseEntity.status(HttpStatus.OK).body(new NewMessageDTO(
                    ai.getChatCompletionMessageAs(
                            text,
                            userBase.getUser(user_id).getMessages(),
                            userBase.getUser(user_id).model,
                            role
                    )
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NewMessageDTO("ERROR: 404"));
        }
    }
}
