package ru.bublinoid.serialization.service;

import ru.bublinoid.serialization.model.TransformedMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataWriter {
    public static void writeJson(List<TransformedMessage> messages, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Включение форматирования JSON
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Создание директорий, если их нет
        mapper.writeValue(file, messages);
    }

    public static void writeXml(List<TransformedMessage> messages, String filePath) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        mapper.writeValue(file, messages);
    }
}
