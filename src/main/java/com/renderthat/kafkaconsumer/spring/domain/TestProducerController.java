package com.renderthat.kafkaconsumer.spring.domain;

import com.obi.cgisolution.schema.Product;
import com.obi.cgisolution.schema.kafka_events;
import com.obi.cgisolution.schema.product_bundle_type;
import com.renderthat.kafkaconsumer.spring.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/product-bundle")
public class TestProducerController {
    private Producer producer;

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish-test")
    public void sendMessageToKafkaTopic2() {
        ProductBundleEntity productBundleEntity = getProductBundleEntity();
        this.producer.sendMessage(productBundleEntity);
    }

    public ProductBundleEntity getProductBundleEntity(){
        ProductBundleEntity productBundleEntity = new ProductBundleEntity();
        productBundleEntity.setProductBundleId("1234");
        productBundleEntity.setContentPieceId("12345");
        productBundleEntity.setContentPieceInternalId("j_123456");
        productBundleEntity.setType(product_bundle_type.ARTICLES);
        productBundleEntity.setEventType(kafka_events.CREATE);

        Product product = new Product();
        product.setIsoSku("de-1234567");
        product.setQty("1");

        List<Product> products = new ArrayList<>();
        products.add(product);
        productBundleEntity.setProducts(products);

        return productBundleEntity;
    }
}
