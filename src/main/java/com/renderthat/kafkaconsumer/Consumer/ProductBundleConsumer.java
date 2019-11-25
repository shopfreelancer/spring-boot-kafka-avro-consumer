package com.renderthat.kafkaconsumer.Consumer;
import com.obi.cgisolution.schema.ProductBundle;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Properties;

@Component
public class ProductBundleConsumer implements ApplicationListener<ApplicationReadyEvent> {
    Logger log = LoggerFactory.getLogger(ProductBundleConsumer.class.getName());

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        initProducer();
    }

    public void initProducer() {
        Properties properties = Config.getConfig();

        KafkaConsumer<String, ProductBundle> kafkaConsumer = new KafkaConsumer<>(properties);
        String topic = env.getProperty("kafka.topic");
        kafkaConsumer.subscribe(Collections.singleton(topic));

        log.info("Waiting for data from kafka...");

        while (true) {
            log.info("Polling");
            ConsumerRecords<String, ProductBundle> records = kafkaConsumer.poll(1000);

            for (ConsumerRecord<String, ProductBundle> record : records) {
                ProductBundle productBundle = record.value();
                log.info(productBundle.toString());
            }

            kafkaConsumer.commitSync();
        }
    }
}
