package ru.bublinoid.serialization.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SmsData {
    @JsonProperty("chat_sessions")
    private List<ChatSession> chatSessions;

}