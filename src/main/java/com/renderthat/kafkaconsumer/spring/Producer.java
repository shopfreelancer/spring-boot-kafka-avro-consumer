package com.renderthat.kafkaconsumer.spring;

import com.obi.cgisolution.schema.ProductBundle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer {
    @Value("${topic.name}")
    private String TOPIC;

    private final KafkaTemplate<String, ProductBundle> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, ProductBundle> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ProductBundle productBundle) {
        this.kafkaTemplate.send(this.TOPIC, productBundle.getProductBundleId(), productBundle);
        log.info(String.format("Produced productBundle -> %s", productBundle.toString()));
    }
}
