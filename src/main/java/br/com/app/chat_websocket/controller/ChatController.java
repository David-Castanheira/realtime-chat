package br.com.app.chat_websocket.controller;

import br.com.app.chat_websocket.domain.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class ChatController {
    @MessageMapping("/concord.sendMessage")
    @SendTo("/topic-channel/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/concord.addUser")
    @SendTo("/topic-channel/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Verifica se o mapa de atributos da sessão é nulo, se sim, inicializa-o
        if (headerAccessor.getSessionAttributes() == null) {
            headerAccessor.setSessionAttributes(new HashMap<>());
        }
        // Por fim, adiciona o usuário pelo username à sessão
        headerAccessor.getSessionAttributes().put("username", chatMessage.username());
        return chatMessage;
    }
}