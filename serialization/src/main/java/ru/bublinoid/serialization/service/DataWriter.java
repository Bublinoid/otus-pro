package ru.bublinoid.serialization.service;

import ru.bublinoid.serialization.model.TransformedMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataWriter {
    public static void writeJson(List<TransformedMessage> messages, String filePath) throws IOException {
        writeFile(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT), messages, filePath);
    }

    public static void writeXml(List<TransformedMessage> messages, String filePath) throws IOException {
        writeFile(new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT), messages, filePath);
    }

    private static <T> void writeFile(ObjectMapper mapper, List<T> messages, String filePath) throws IOException {
        Files.createDirectories(Paths.get(filePath).getParent());
        File file = new File(filePath);
        mapper.writeValue(file, messages);
    }
}
