package com.renderthat.kafkaconsumer.spring;

import com.obi.cgisolution.schema.ProductBundle;
import com.renderthat.kafkaconsumer.spring.domain.ProductBundleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    ProductBundleRepository productBundleRepository;

    @Autowired
    public void setProductBundleRepository(ProductBundleRepository productBundleRepository) {
        this.productBundleRepository = productBundleRepository;
    }

    @Value("${topic.name}")
    private String topicName;

    @KafkaListener(topics = "cgisolution-productbundles", groupId = "group_id_2")
    public void receive(ConsumerRecord<String, ProductBundle> record) {

        ProductBundle productBundle = record.value();
        log.info("Received avro: "+productBundle.toString());

        productBundleRepository.save(productBundle);
    }
}