package models;

public class Message {
    private String senderId; // ID urządzenia nadawczego
    private String recipientId; // ID urządzenia odbiorczego
    private String content; // Treść wiadomości

    public Message(String senderId, String recipientId, String content) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getContent() {
        return content;
    }
}

