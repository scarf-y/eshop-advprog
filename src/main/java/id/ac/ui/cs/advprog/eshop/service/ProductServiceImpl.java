package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.createProduct(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProduct = new ArrayList<>();
        Iterator<Product> productIterator = productRepository.findAll();
        productIterator.forEachRemaining(allProduct::add);

        return allProduct;
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Product update(String id, Product updatedProduct) {
        return productRepository.updateProduct(id, updatedProduct);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteProduct(id);
    }
}
