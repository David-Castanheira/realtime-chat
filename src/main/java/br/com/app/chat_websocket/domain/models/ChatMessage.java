package br.com.app.chat_websocket.domain.models;

import java.util.UUID;

public record ChatMessage(
        UUID id,
        String username,
        String message,
        MessageType messageType
        ) {

        public static ChatMessage join(String username) {
                return new ChatMessage(UUID.randomUUID(), username, "join a this chat", MessageType.JOIN);
        }

        public static ChatMessage chat(String username) {
                return new ChatMessage(UUID.randomUUID(), username, "is in a chat", MessageType.CHAT);
        }

        public static ChatMessage typing(String username) {
                return new ChatMessage(UUID.randomUUID(), username, "is typing", MessageType.TYPING);
        }

        public static ChatMessage online(String username) {
                return new ChatMessage(UUID.randomUUID(), username, "is online", MessageType.ONLINE);
        }

        public static ChatMessage offline(String username) {
                return new ChatMessage(UUID.randomUUID(), username, "is offline", MessageType.OFFLINE);
        }

        public static ChatMessage leave(String username) {
                return new ChatMessage(UUID.randomUUID(), username, "leave this chat", MessageType.LEAVE);
        }
}
