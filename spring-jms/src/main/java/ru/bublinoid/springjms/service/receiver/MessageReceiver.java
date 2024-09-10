package ru.bublinoid.springjms.service.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.bublinoid.springjms.entity.Message;

@Component
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @JmsListener(destination = "message-topic")
    public void receiveMessage(Message message) {
        logger.info("Received message: {}", message.getText());
        logger.info("Message hash: {}", message.getHash());
    }
}
