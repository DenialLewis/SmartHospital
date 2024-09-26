package com.example.smart_hospital.controller;

import com.example.smart_hospital.service.AIChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private AIChatService aiChatService;

    @PostMapping
    public String chat(@RequestBody ChatRequest chatRequest) throws Exception {
        return aiChatService.getAIResponse(chatRequest.getMessage());
    }
}

class ChatRequest {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
