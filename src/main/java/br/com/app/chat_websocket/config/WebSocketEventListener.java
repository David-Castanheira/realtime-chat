package br.com.app.chat_websocket.config;

import br.com.app.chat_websocket.domain.models.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void webSocketConnectEventListener(SessionConnectEvent connectEvent) {
        log.info("Connection has been initialized successfully");
    }

//    @EventListener
//    public void webSocketOnlineEventListener() {
//        // TODO
//    }
//
//    @EventListener
//    public void webSocketOfflineEventListener() {
//        // TODO
//    }
//
//    @EventListener
//    public void webSocketTypeMessageEventListener() {
//        // TODO
//    }

    @EventListener
    public void webSocketDisconnectEventListener(SessionDisconnectEvent disconnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        // Verifica se o usuário existe para desconectá-lo
        if (username != null) {
            log.info("User disconnected: {}", username);
            var chatMessage = ChatMessage.leave(username);

            // Envia o conteúdo da mensagem para um tópico público
            messageTemplate.convertAndSend("/topic-channel/public", chatMessage);
        }
    }
}
