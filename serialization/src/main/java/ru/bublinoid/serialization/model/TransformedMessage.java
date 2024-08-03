package ru.bublinoid.serialization.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransformedMessage {
    private String chatIdentifier;
    private String memberLastName;
    private String belongNumber;
    private LocalDateTime sendDate;
    private String text;

    public TransformedMessage(String chatIdentifier, String memberLastName, String belongNumber, LocalDateTime sendDate, String text) {
        this.chatIdentifier = chatIdentifier;
        this.memberLastName = memberLastName;
        this.belongNumber = belongNumber;
        this.sendDate = sendDate;
        this.text = text;
    }
}
