package com.example.websocketchatserver.service;


import com.example.websocketchatserver.model.ChatMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void produce(ChannelTopic topic, ChatMessage message) {
        kafkaTemplate.send(topic.getTopic(), message);
    }
}
