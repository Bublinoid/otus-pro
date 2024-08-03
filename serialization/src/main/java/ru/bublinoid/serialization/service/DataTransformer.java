package ru.bublinoid.serialization.service;

import ru.bublinoid.serialization.model.SmsData;
import ru.bublinoid.serialization.model.TransformedMessage;
import ru.bublinoid.serialization.model.ChatSession;
import ru.bublinoid.serialization.model.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataTransformer {
    public static List<TransformedMessage> transform(SmsData smsData) {
        List<TransformedMessage> transformedMessages = new ArrayList<>();

        for (ChatSession chatSession : smsData.getChatSessions()) {
            String chatIdentifier = chatSession.getChatIdentifier();
            String memberLastName = chatSession.getMembers().get(0).getLastName();

            for (Message message : chatSession.getMessages()) {
                TransformedMessage transformedMessage = new TransformedMessage(
                        chatIdentifier, memberLastName, message.getBelongNumber(),
                        message.getSendDate(), message.getText()
                );
                transformedMessages.add(transformedMessage);
            }
        }

        return transformedMessages.stream()
                .distinct()
                .sorted(Comparator.comparing(TransformedMessage::getSendDate))
                .collect(Collectors.toList());
    }
}
