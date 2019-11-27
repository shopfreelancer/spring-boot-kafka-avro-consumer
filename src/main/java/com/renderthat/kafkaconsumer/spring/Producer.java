package com.renderthat.kafkaconsumer.spring;

import com.obi.cgisolution.schema.ProductBundle;
import com.renderthat.kafkaconsumer.spring.domain.ProductBundleEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

    public void sendMessage(ProductBundleEntity productBundleEntity) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ProductBundle productBundle = modelMapper.map(productBundleEntity, ProductBundle.class);

        this.kafkaTemplate.send(this.TOPIC, productBundle.getProductBundleId(), productBundle);
        log.info(String.format("Produced productBundle -> %s", productBundle.toString()));
    }
}
