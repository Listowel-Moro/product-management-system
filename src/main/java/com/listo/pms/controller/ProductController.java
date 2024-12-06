package com.listo.pms.controller;

import com.listo.pms.dto.ProductBody;
import com.listo.pms.model.Product;
import com.listo.pms.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Add a new product", description = "Create a new product in the DB")
    @PostMapping("/add")
    public Product postProduct(@RequestBody ProductBody productDTO){
        return productService.saveProduct(productDTO);
    }

    @Operation(summary = "Fetch all products", description = "Fetch all products in the DB")
    @GetMapping("")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @Operation(summary = "Fetch sorted paginated products", description = "Fetch and paginate products in the DB")
    @GetMapping("/sort")
    public Page<Product> getSortedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return productService.getSortedProducts(page, size, sortBy, sortDir);
    }

    @Operation(summary = "Fetch a product", description = "Fetch a single product from the DB")
    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @Operation(summary = "Delete a product", description = "Delete a single product from the DB")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return "Product with id " + id + " deleted successfully";
    }

    @Operation(summary = "Update a product", description = "Update a product from the DB")
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody ProductBody productBody){
        return productService.updateProduct(id, productBody);
    }

    @Operation(summary = "Fetch all products", description = "Fetch all products in the Product Tree")
    @GetMapping("/tree")
    public List<Product> getSortedFromTree(){
        return productService.getSortedDataFromTree();
    }

    @Operation(summary = "Delete a product", description = "Delete a product from the Product Tree and DB")
    @DeleteMapping("/tree/{name}")
    public String deleteProductFromTree(@PathVariable String name){
        productService.deleteProductByName(name);
        return "Product " + name + " successfully deleted";
    }

    @Operation(summary = "Fetch a product", description = "Fetch a single product from the Product tree")
    @GetMapping("/tree/{name}")
    public Product getProductFromTree(@PathVariable String name){
        return productService.getProductFromTree(name);
    }
}
