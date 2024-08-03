package ru.bublinoid.serialization.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Member {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("handle_id")
    private String handleId;

    @JsonProperty("image_path")
    private String imagePath;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("service")
    private String service;

    @JsonProperty("thumb_path")
    private String thumbPath;
}
