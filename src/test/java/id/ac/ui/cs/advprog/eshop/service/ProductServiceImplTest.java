package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
    }

    @Test
    void testCreate() {
        when(productRepository.createProduct(product)).thenReturn(product);
        Product result = productService.create(product);
        verify(productRepository).createProduct(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(product);
        Iterator<Product> iterator = products.iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> result = productService.findAll();
        verify(productRepository).findAll();
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    void testFindById() {
        when(productRepository.findById("123")).thenReturn(product);
        Product result = productService.findById("123");
        verify(productRepository).findById("123");
        assertEquals(product, result);
    }

    @Test
    void testUpdate() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        // When updating, the repository sets the same id on the updated product.
        when(productRepository.updateProduct("123", updatedProduct)).thenReturn(updatedProduct);
        Product result = productService.update("123", updatedProduct);
        verify(productRepository).updateProduct("123", updatedProduct);
        assertEquals(updatedProduct, result);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).deleteProduct("123");
        productService.delete("123");
        verify(productRepository).deleteProduct("123");
    }
}
