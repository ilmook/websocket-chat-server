package com.example.websocketchatserver.controller;

import com.example.websocketchatserver.model.ChatMessage;
import com.example.websocketchatserver.repo.ChatRoomRepository;
import com.example.websocketchatserver.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final KafkaProducerService producerService;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        producerService.produce(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}

