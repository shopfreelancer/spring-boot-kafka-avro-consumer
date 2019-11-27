package com.renderthat.kafkaconsumer.spring.domain;

import com.obi.cgisolution.schema.ProductBundle;
import org.springframework.data.repository.CrudRepository;

public interface ProductBundleRepository extends CrudRepository<ProductBundle, Integer> {
}
