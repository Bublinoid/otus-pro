package ru.bublinoid.serialization.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    @JsonProperty("belong_number")
    private String belongNumber;

    @JsonProperty("send_date")
    private String sendDate;

    @JsonProperty("text")
    private String text;

    @JsonProperty("ROWID")
    private String rowid;

    @JsonProperty("attributedBody")
    private String attributedBody;

    @JsonProperty("date")
    private String date;

    @JsonProperty("date_read")
    private String dateRead;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("handle_id")
    private String handleId;

    @JsonProperty("has_dd_results")
    private String hasDdResults;

    @JsonProperty("is_deleted")
    private String isDeleted;

    @JsonProperty("is_from_me")
    private String isFromMe;

    @JsonProperty("send_status")
    private String sendStatus;

    @JsonProperty("service")
    private String service;

}
