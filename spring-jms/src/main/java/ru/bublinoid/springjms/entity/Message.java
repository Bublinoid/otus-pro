package ru.bublinoid.springjms.entity;

import lombok.Data;
import ru.bublinoid.springjms.utils.HashField;
import ru.bublinoid.springjms.utils.Historical;
import java.io.Serializable;
import java.util.UUID;

@Data
public class Message implements Historical, Serializable {
    private static final long serialVersionUID = 1L;

    @HashField
    private UUID uuid;

    @HashField
    private String text;

    private transient UUID hash;

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
