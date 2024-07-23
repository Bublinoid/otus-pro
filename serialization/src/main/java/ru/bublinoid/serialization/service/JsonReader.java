package ru.bublinoid.serialization.service;

import ru.bublinoid.serialization.model.SmsData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;

public class JsonReader {
    public static SmsData readJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(filePath);
        try (InputStream inputStream = resource.getInputStream()) {
            return mapper.readValue(inputStream, SmsData.class);
        }
    }
}
