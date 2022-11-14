package com.example.websocketchatserver.service;


import com.example.websocketchatserver.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final SimpMessageSendingOperations messagingTemplate;

    @KafkaListener(topics = "testTopic", groupId = "testgroup", containerFactory = "kafkaListener")
    public void consume(ChatMessage message) throws MessagingException {
        System.out.println("카프카 컨슈머 = " + message.getMessage());

        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }

        messagingTemplate.convertAndSend("/sub/chat/room/"+message.getRoomId(), message );
    }
}

