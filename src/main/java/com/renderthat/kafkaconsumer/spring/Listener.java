package com.renderthat.kafkaconsumer.spring;

import com.obi.cgisolution.schema.ProductBundle;
import com.renderthat.kafkaconsumer.spring.domain.ProductBundleEntity;
import com.renderthat.kafkaconsumer.spring.domain.ProductBundleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class Listener {

    ProductBundleRepository productBundleRepository;

    @Autowired
    public void setProductBundleRepository(ProductBundleRepository productBundleRepository) {
        this.productBundleRepository = productBundleRepository;
    }

    @Value("${topic.name}")
    private String topicName;

    @KafkaListener(topics = "cgisolution-productbundles", groupId = "group_id_2")
    public void consume(ConsumerRecord<String, ProductBundle> record) {

        ProductBundle productBundle = record.value();
        log.info(productBundle.toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ProductBundleEntity productBundleEntity = modelMapper.map(productBundle, ProductBundleEntity.class);
        log.info(productBundleEntity.toString());
        productBundleRepository.save(productBundleEntity);

    }
}