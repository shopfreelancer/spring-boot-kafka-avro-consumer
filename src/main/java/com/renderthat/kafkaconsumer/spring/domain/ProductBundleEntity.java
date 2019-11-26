package com.renderthat.kafkaconsumer.spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection = "productBundleEntity")
public class ProductBundleEntity {
    @Id
    private String id;
    private String productBundleId;
    private String contentPieceId;
    private String contentPieceInternalId;
    private String type;
}
