package br.com.app.chat_websocket.domain.models;

public record ChatMessage(
        String username,
        String message,
        MessageType messageType
        ) {
}
