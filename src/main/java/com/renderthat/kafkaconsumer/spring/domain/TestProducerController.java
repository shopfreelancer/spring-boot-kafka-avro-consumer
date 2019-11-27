package com.renderthat.kafkaconsumer.spring.domain;

import com.obi.cgisolution.schema.Product;
import com.obi.cgisolution.schema.ProductBundle;
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
    public void sendMessageToKafkaTopic() {
        ProductBundle productBundle = getProductBundle();
        this.producer.sendMessage(productBundle);
    }

    public ProductBundle getProductBundle(){
        ProductBundle productBundle = new ProductBundle();
        productBundle.setProductBundleId("1234");
        productBundle.setContentPieceId("12345");
        productBundle.setContentPieceInternalId("j_123456");
        productBundle.setType(product_bundle_type.ARTICLES);
        productBundle.setEventType(kafka_events.CREATE);

        Product product = new Product();
        product.setIsoSku("de-1234567");
        product.setQty("1");

        List<Product> products = new ArrayList<>();
        products.add(product);
        productBundle.setProducts(products);

        return productBundle;
    }
}
