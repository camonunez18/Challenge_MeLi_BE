package com.meli.challenge.service;

import com.meli.challenge.model.Product;
import com.meli.challenge.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void testGetProductById_ProductFound() {
        Product mockProduct = getMockProduct();
        when(productRepository.findById(1L)).thenReturn(of(mockProduct));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals(mockProduct.getName(), result.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_ProductNotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById(2L);

        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(2L);
    }

    @Test
    void testGetProductById_RepositoryException() {
        when(productRepository.findById(anyLong())).thenThrow(new RuntimeException("Simulated DB error"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1L);
        }, "Debería lanzar una RuntimeException");

        assertEquals("Simulated DB error", thrown.getMessage());
        verify(productRepository, times(1)).findById(1L);
    }

    private Product getMockProduct() {
        return Product.builder()
                .id(1L)
                .description(Product.Description.builder().build())
                .name("Test Product")
                .build();
    }
}