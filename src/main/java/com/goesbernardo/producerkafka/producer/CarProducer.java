package com.goesbernardo.producerkafka.producer;

import com.goesbernardo.producerkafka.dto.CarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarProducer {

    private static final Logger logger = LoggerFactory.getLogger(CarProducer.class);

    private final String topic;
    private final KafkaTemplate<String, CarDTO> kafkaTemplate;

    public CarProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, CarDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CarDTO carDTO) {
        kafkaTemplate.send(topic,carDTO).addCallback(result -> logger.info("message send"),
                                                    failure -> logger.info("message failure"));
    }
}
