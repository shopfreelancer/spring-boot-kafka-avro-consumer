package com.renderthat.kafkaconsumer.Consumer;

import com.obi.cgisolution.schema.ProductBundle;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.SerializationException;
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

        try {
            while (true) {
                log.info("Polling");

                try {
                    ConsumerRecords<String, ProductBundle> records = kafkaConsumer.poll(1000);
                    consumeRecords(records, kafkaConsumer);
                } catch (Exception e) {
                    log.error("Error during polling "+  e.getMessage());

                }

            }
        } finally {
            kafkaConsumer.close();
        }
    }

    public void consumeRecords(ConsumerRecords<String, ProductBundle> records, KafkaConsumer kafkaConsumer){
        Integer recordCount = records.count();

        for (ConsumerRecord<String, ProductBundle> record : records) {

            ProductBundle specificRecord = record.value();
            log.info(specificRecord.toString());
            log.info(record.getClass().toString());
        }

        if (recordCount > 0) {
            //BulkResponse bulkItemResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info("Committing offsets...");
            kafkaConsumer.commitSync();
            log.info("Offsets have been committed");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}
