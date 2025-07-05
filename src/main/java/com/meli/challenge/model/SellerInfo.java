package com.meli.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerInfo {

    private String name;
    private String contact;
    private Double rating;
    private String reputation;
    private String logoUrl;

}
