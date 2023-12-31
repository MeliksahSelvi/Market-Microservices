package com.meliksah.orderservice.config;

import com.meliksah.orderservice.event.OrderPlacedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mselvi
 * @Created 02.08.2023
 */

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    @Value("${market.kafka.address.producer}")
    private String kafkaAddress;

    @Bean
    public KafkaTemplate<String, OrderPlacedEvent> kafkaTemplateNotification() {
        return new KafkaTemplate<>(producerFactoryNotification());
    }

    @Bean
    public ProducerFactory<String, OrderPlacedEvent> producerFactoryNotification() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }
}
