package com.renderthat.kafkaconsumer.spring;

import com.obi.cgisolution.schema.ProductBundle;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class Listener {

    @Value("${topic.name}")
    private String topicName;

    @KafkaListener(topics = "cgisolution-productbundles", groupId = "group_id_2")
    public void consume(ConsumerRecord<String, ProductBundle> record) {
        ProductBundle specificRecord = record.value();
    }
}