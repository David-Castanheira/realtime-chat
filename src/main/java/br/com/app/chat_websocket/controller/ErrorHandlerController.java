package br.com.app.chat_websocket.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ErrorHandlerController {

    @MessageExceptionHandler
    @SendTo("/topic-channel/errors")
    public String handleException(Exception e) {
        return "Error: " + e.getMessage();
    }
}
