package ru.bublinoid.springjms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bublinoid.springjms.entity.Message;
import ru.bublinoid.springjms.service.sender.MessageSender;

import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageSender messageSender;

    public MessageController(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String text) {
        Message message = new Message(UUID.randomUUID(), text);
        messageSender.sendMessage("message-topic", message);
        logger.info("Message sent with text: {} and hash: {}", message.getText(), message.getHash());
        return "Message sent with hash: " + message.getHash();
    }
}
