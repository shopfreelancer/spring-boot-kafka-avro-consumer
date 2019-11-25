package com.renderthat.kafkaconsumer.Consumer;
import com.obi.cgisolution.schema.ProductBundle;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Properties;

@Component
public class ProductBundleConsumer implements ApplicationListener<ApplicationReadyEvent> {
    Logger log = LoggerFactory.getLogger(ProductBundleConsumer.class.getName());

    private Config config;

    @Autowired
    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        initConsumer();
    }

    public void initConsumer() {
        Properties properties = config.getProperties();

        KafkaConsumer<String, ProductBundle> kafkaConsumer = new KafkaConsumer<>(properties);
        String topic = config.getTopic();
        kafkaConsumer.subscribe(Collections.singleton(topic));

        log.info("Waiting for data from kafka...");

        while (true) {
            log.info("Polling");
            ConsumerRecords<String, ProductBundle> records = kafkaConsumer.poll(1000);

            for (ConsumerRecord<String, ProductBundle> record : records) {
                try {
                    ProductBundle specificRecord = record.value();
                    log.info(specificRecord.toString());
                    log.info(record.getClass().toString());
                } catch (Exception e){
                    log.error("error while consuming kafka " + e.getMessage());
                }
            }

            kafkaConsumer.commitSync();
        }
    }
}
