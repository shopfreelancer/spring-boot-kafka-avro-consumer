package com.renderthat.kafkaconsumer.spring.domain;

import com.obi.cgisolution.schema.ProductBundle;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "productBundleEntity")
public class ProductBundleEntity extends ProductBundle {

}
