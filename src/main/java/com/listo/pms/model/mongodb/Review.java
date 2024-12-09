package com.listo.pms.model.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_reviews")
@Data
public class Review {
    @Id
    private String id;
    private String productId;
    private String reviewerName;
    private String comment;
    private int rating;

}

