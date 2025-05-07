package br.com.app.chat_websocket.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String username;
    private String message;
    private MessageType messageType;
}
