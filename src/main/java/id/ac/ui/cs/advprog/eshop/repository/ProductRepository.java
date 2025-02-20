package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();
    private static final String ERRORPREFIX = "Product with ID ";
    private static final String ERRORSUFFIX= " not found.";

    public Product createProduct(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product updateProduct(String id, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                updatedProduct.setProductId(id);
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        throw new RuntimeException(ERRORPREFIX + id + ERRORSUFFIX); // Jika ID tidak ditemukan
    }

    public Product findById(String id) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ERRORPREFIX + id + ERRORSUFFIX));
    }

    public void deleteProduct(String id) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                productData.remove(i);
                return;
            }
        }
        throw new RuntimeException(ERRORPREFIX + id + ERRORSUFFIX);
    }
}
