package br.com.app.chat_websocket.domain.models;

import java.util.Optional;

public record ChatMessage(
        String username,
        String message,
        MessageType messageType
        ) {

        public static ChatMessage join(String username) {
                return new ChatMessage(username, "join a this chat", MessageType.JOIN);
        }

        public static ChatMessage chat(String username) {
                return new ChatMessage(username, "is in a chat", MessageType.CHAT);
        }

        public static ChatMessage leave(String username) {
                return new ChatMessage(username, "leave this chat", MessageType.LEAVE);
        }
}
