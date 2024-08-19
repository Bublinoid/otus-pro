package ru.bublinoid.springjms.entity;

import lombok.Data;
import ru.bublinoid.springjms.utils.HashField;
import ru.bublinoid.springjms.utils.Historical;

import java.util.UUID;

@Data
public class Message implements Historical {
    @HashField
    private UUID uuid;

    @HashField
    private String text;

    private UUID hash;

    public Message() {}

    public Message(UUID uuid, String text) {
        this.uuid = uuid;
        this.text = text;
        this.hash = generateHash();
    }

    @Override
    public void setHash(UUID uuid) {
        this.hash = uuid;
    }

    @Override
    public UUID getHash() {
        return this.hash;
    }
}
