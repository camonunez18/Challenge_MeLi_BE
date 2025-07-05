package com.meli.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Description description;
    private Double price;
    private Double originalPrice;
    private Double discountPercentage;
    private List<String> images;
    private List<Color> colors;
    private List<Comment> comments;
    private Integer totalReviews;
    private SellerInfo sellerInfo;
    private List<Characteristics> characteristics;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Description {
        private String shortDescription;
        private String fullDescription;
    }
}
