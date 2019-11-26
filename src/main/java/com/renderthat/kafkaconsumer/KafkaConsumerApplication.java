package com.renderthat.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.obi.cgisolution.schema", "com.renderthat.kafkaconsumer"})
@SpringBootApplication()
public class KafkaConsumerApplication{

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
