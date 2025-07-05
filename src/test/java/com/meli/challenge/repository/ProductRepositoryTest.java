package com.meli.challenge.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.challenge.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {

    private ResourceLoader resourceLoader;
    private ObjectMapper objectMapper;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        resourceLoader = mock(ResourceLoader.class);
        objectMapper = mock(ObjectMapper.class);
        productRepository = new ProductRepository(resourceLoader, objectMapper);
    }

    @Test
    void testFindById_WhenProductExists_ReturnsProduct() throws Exception {
        Product product = Product.builder()
                .id(1L)
                .build();

        String json = "{\"id\":1}";
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());

        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenReturn(inputStream);
        when(resourceLoader.getResource("classpath:data/product.json")).thenReturn(resource);
        when(objectMapper.readValue(any(InputStream.class), eq(Product.class))).thenReturn(product);

        Optional<Product> result = productRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindById_WhenProductIdDoesNotMatch_ReturnsEmpty() throws Exception {
        Long searchedId = 2L;
        Product product = Product.builder()
                .id(1L)
                .build();

        String json = "{\"id\":1}";
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());

        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenReturn(inputStream);
        when(resourceLoader.getResource("classpath:data/product.json")).thenReturn(resource);
        when(objectMapper.readValue(any(InputStream.class), eq(Product.class))).thenReturn(product);

        Optional<Product> result = productRepository.findById(searchedId);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindById_WhenIOExceptionOccurs_ReturnsEmpty() throws Exception {
        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenThrow(new IOException("Test IO Exception"));
        when(resourceLoader.getResource("classpath:data/product.json")).thenReturn(resource);

        Optional<Product> result = productRepository.findById(1L);

        assertFalse(result.isPresent());
    }
}
