package com.example.chatapp;

public class Messages {


    private String message;
    private String type;
    private  String senderId;
    private String receiverID;
    private String chatID;


    public Messages() {
    }


    public Messages(String message, String type,String  senderId, String receiverID, String chatID) {
        this.message = message;
        this.type = type;
        this.senderId = senderId;
        this.receiverID = receiverID;
        this.chatID  = chatID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }
}
