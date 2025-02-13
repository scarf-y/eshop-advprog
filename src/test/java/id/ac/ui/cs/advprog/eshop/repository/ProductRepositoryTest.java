package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af3bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.createProduct(product);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());
        Product savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af3bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.createProduct(product1);

        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-9999-71af6af3bd7");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.createProduct(product2);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());
        Product savedProduct = iterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());

        savedProduct = iterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
        assertFalse(iterator.hasNext());
    }
}
