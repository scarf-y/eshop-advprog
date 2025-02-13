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

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductName("Intel Evo i7");
        product.setProductQuantity(10);

        // Note: createProduct will set the product id with random UUID
        productRepository.createProduct(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Motor Bebek Angsa");
        updatedProduct.setProductQuantity(50);

        Product result = productRepository.updateProduct(product.getProductId(), updatedProduct);

        // Assert that the product was updated correctly
        assertEquals(product.getProductId(), result.getProductId()); // ID should remain unchanged
        assertEquals("Motor Bebek Angsa", result.getProductName());
        assertEquals(50, result.getProductQuantity());

        // The old product name and quantity should be different now
        assertNotEquals(product.getProductName(), result.getProductName());
        assertNotEquals(product.getProductQuantity(), result.getProductQuantity());
    }

    @Test
    void testUpdateProductNotFound() {
        // Attempt to update a non-existent product
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Nonexistent Product");
        updatedProduct.setProductQuantity(100);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productRepository.updateProduct("nonexistent-id", updatedProduct);
        });

        assertEquals("Product with ID nonexistent-id not found.", exception.getMessage());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Temporary Product");
        product.setProductQuantity(20);

        productRepository.createProduct(product);

        productRepository.deleteProduct(product.getProductId());

        // Verify product is deleted
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteProductNotFound() {
        // Attempt to delete a non-existent product
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productRepository.deleteProduct("nonexistent-id");
        });

        assertEquals("Product with ID nonexistent-id not found.", exception.getMessage());
    }

}
