package ru.bublinoid.serialization.model;

import lombok.Data;

import java.util.Objects;
@Data
public class TransformedMessage {
    private String chatIdentifier;
    private String memberLastName;
    private String belongNumber;
    private String sendDate;
    private String text;

    public TransformedMessage(String chatIdentifier, String memberLastName, String belongNumber, String sendDate, String text) {
        this.chatIdentifier = chatIdentifier;
        this.memberLastName = memberLastName;
        this.belongNumber = belongNumber;
        this.sendDate = sendDate;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransformedMessage that = (TransformedMessage) o;
        return Objects.equals(chatIdentifier, that.chatIdentifier) &&
                Objects.equals(memberLastName, that.memberLastName) &&
                Objects.equals(belongNumber, that.belongNumber) &&
                Objects.equals(sendDate, that.sendDate) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatIdentifier, memberLastName, belongNumber, sendDate, text);
    }
}
