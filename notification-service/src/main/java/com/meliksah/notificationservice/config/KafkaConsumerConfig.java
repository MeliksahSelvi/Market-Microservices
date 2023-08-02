package com.meliksah.notificationservice.config;

import com.meliksah.notificationservice.event.OrderPlacedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mselvi
 * @Created 02.08.2023
 */

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${market.kafka.address}")
    private String kafkaAddress;

    @Value("${market.kafka.group-id}")
    private String groupId;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent> kafkaListenerContainerFactoryNotification() {
        ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryNotification());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, OrderPlacedEvent> consumerFactoryNotification() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderPlacedEvent.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }
}
