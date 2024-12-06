package com.listo.pms.controller;

import com.listo.pms.dto.ProductBody;
import com.listo.pms.model.Product;
import com.listo.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product postProduct(@RequestBody ProductBody productDTO){
        return productService.saveProduct(productDTO);
    }

    @GetMapping("")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/sort")
    public Page<Product> getSortedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return productService.getSortedProducts(page, size, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return "Product with id " + id + " deleted successfully";
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody ProductBody productBody){
        return productService.updateProduct(id, productBody);
    }
}
