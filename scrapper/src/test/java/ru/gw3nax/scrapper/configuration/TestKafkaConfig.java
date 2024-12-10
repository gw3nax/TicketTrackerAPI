package ru.gw3nax.scrapper.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.gw3nax.scrapper.dto.request.FlightRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class TestKafkaConfig {

    @Value("${test.kafka-bootstrap-server}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, FlightRequest> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, FlightRequest> testKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
