package com.renderthat.kafkaconsumer.spring;

import com.obi.cgisolution.schema.ProductBundle;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);
/*
    @KafkaListener(topics = "${kafka.topic}")
    public void receive(@Payload String message,
                        @Headers MessageHeaders headers) {
        LOG.info("received message='{}'", message);
        headers.keySet().forEach(key -> LOG.info("{}: {}", key, headers.get(key)));
    }
    */

    @KafkaListener(topics = "${kafka.topic}", groupId = "group_id")
    public void consume(ConsumerRecord<String, ProductBundle> record) {
        LOG.info(String.format("Consumed message -> %s", record.value()));
    }

}
