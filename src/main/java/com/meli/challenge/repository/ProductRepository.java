package com.meli.challenge.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.challenge.model.Product;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
@Repository
@AllArgsConstructor
public class ProductRepository {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    public Optional<Product> findById(Long id) {
        Product cachedProduct;
        try {
            Resource resource = resourceLoader.getResource("classpath:data/product.json");
            try (InputStream is = resource.getInputStream()) {
                cachedProduct = objectMapper.readValue(is, Product.class);

                if (cachedProduct != null && cachedProduct.getId().equals(id)) {
                    log.info("Product data loaded successfully from JSON by ProductRepository.");
                    return of(cachedProduct);
                }
                log.error("Product data not found.");
                return empty();
            }
        } catch (IOException e) {
            log.error("Failed to load product data from JSON file: " + e.getMessage());
            e.printStackTrace();
        }
        return empty();
    }

}
