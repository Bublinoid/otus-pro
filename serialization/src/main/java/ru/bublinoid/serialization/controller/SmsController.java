package ru.bublinoid.serialization.controller;

import ru.bublinoid.serialization.model.SmsData;
import ru.bublinoid.serialization.model.TransformedMessage;
import ru.bublinoid.serialization.service.DataTransformer;
import ru.bublinoid.serialization.service.JsonReader;
import ru.bublinoid.serialization.service.DataWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Value("${sms.file.path}")
    private String filePath;

    @Value("${sms.file.output.json}")
    private String jsonOutputPath;

    @Value("${sms.file.output.xml}")
    private String xmlOutputPath;

    @GetMapping(value = "/transformed", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<TransformedMessage> getTransformedMessages(@RequestParam(defaultValue = "json") String format) throws IOException {
        SmsData smsData = JsonReader.readJson(filePath);
        return DataTransformer.transform(smsData);
    }

    @GetMapping("/saveJson")
    public String saveTransformedMessagesAsJson() throws IOException {
        SmsData smsData = JsonReader.readJson(filePath);
        List<TransformedMessage> transformedMessages = DataTransformer.transform(smsData);
        DataWriter.writeJson(transformedMessages, jsonOutputPath);
        return "Transformed messages saved as JSON.";
    }

    @GetMapping("/saveXml")
    public String saveTransformedMessagesAsXml() throws IOException {
        SmsData smsData = JsonReader.readJson(filePath);
        List<TransformedMessage> transformedMessages = DataTransformer.transform(smsData);
        DataWriter.writeXml(transformedMessages, xmlOutputPath);
        return "Transformed messages saved as XML.";
    }
}
