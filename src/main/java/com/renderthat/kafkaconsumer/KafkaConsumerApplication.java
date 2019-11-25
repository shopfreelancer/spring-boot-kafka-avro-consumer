package com.renderthat.kafkaconsumer;

import com.obi.cgisolution.schema.ProductBundle;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.obi.cgisolution.schema", "com.renderthat.kafkaconsumer"})

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
})
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	@Bean
	@ConditionalOnProperty(name = "SpecificRecord", matchIfMissing = true)
	public SpecificRecord class1Service() {
		return new ProductBundle();
	}

}
