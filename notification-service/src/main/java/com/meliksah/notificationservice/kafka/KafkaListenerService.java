package com.meliksah.notificationservice.kafka;

import com.meliksah.notificationservice.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @Author mselvi
 * @Created 02.08.2023
 */

@Service
@Slf4j
public class KafkaListenerService {


    @KafkaListener(topics = "notificationTopic",groupId ="${market.kafka.group-id}",containerFactory = "kafkaListenerContainerFactoryNotification")
    public void handleNotification(@Payload OrderPlacedEvent orderPlacedEvent) {
        //todo implement notification
        log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
    }
}
