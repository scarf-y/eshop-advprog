package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Build a standalone setup of the controller without loading the full Spring context.
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        when(service.create(any(Product.class))).thenReturn(new Product());

        mockMvc.perform(post("/product/create")
                        .param("name", "Test Product"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service).create(any(Product.class));
    }

    @Test
    public void testProductListPage() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        when(service.findAll()).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    public void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        when(service.findById("123")).thenReturn(product);

        mockMvc.perform(get("/product/edit-product/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    public void testEditProductPost() throws Exception {
        when(service.update(eq("123"), any(Product.class))).thenReturn(new Product());

        mockMvc.perform(post("/product/edit-product/123")
                        .param("name", "Updated Product"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service).update(eq("123"), any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(post("/product/delete-product/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service).delete("123");
    }
}
