package com.renderthat.kafkaconsumer.spring;


import com.obi.cgisolution.schema.ProductBundle;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog(topic = "Consumer Logger")
public class Consumer {

    @Value("${topic.name}")
    private String topicName;

    @KafkaListener(topics = "cgisolution-productbundles", groupId = "group_id")
    public void consume(ConsumerRecord<String, ProductBundle> record) {
        log.info(String.format("Consumed message -> %s", record.value()));
    }
}