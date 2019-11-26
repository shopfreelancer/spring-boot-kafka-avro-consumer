package com.renderthat.kafkaconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;


//@ComponentScan({"com.obi.cgisolution.schema", "com.renderthat.kafkaconsumer.spring"})

@SpringBootApplication()
public class KafkaConsumerApplication{

	@Value("${topic.name}")
	private String topicName;

	@Value("${topic.partitions-num}")
	private Integer partitions;

	@Value("${topic.replication-factor}")
	private short replicationFactor;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}


}
