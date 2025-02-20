package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit-product/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model) {
        // Retrieve the product by ID. Ensure the service returns the product or throws a proper exception if not found.
        Product existingProduct = service.findById(id);
        model.addAttribute("product", existingProduct);
        return "editProduct";
    }

    @PostMapping("/edit-product/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product updatedProduct, Model model) {
        service.update(id, updatedProduct);
        return "redirect:/product/list";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") String id, Model model) {
        service.delete(id);
        return "redirect:/product/list";
    }
}
