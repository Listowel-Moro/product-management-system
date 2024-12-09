package com.listo.pms.controller;

import com.listo.pms.dto.ProductBody;
import com.listo.pms.model.Product;
import com.listo.pms.service.ProductService;
import com.listo.pms.util.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Add a new product", description = "Create a new product in the DB")
    @PostMapping("/add")
    public ResponseEntity<?> postProduct(@RequestBody ProductBody productDTO) {
        Product savedProduct = productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseObject.createResponse("Product added successfully!", savedProduct, 201));
    }

    @Operation(summary = "Fetch all products", description = "Fetch all products in the DB")
    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(ResponseObject.createResponse("Products fetched successfully!", products, 200));
    }

    @Operation(summary = "Fetch sorted paginated products", description = "Fetch and paginate products in the DB")
    @GetMapping("/sort")
    public ResponseEntity<?> getSortedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<Product> sortedProducts = productService.getSortedProducts(page, size, sortBy, sortDir);
        return ResponseEntity.ok(ResponseObject.createResponse("Products fetched and sorted successfully!", sortedProducts, 200));
    }

    @Operation(summary = "Fetch a product", description = "Fetch a single product from the DB")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Delete a product", description = "Delete a single product from the DB")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
            productService.deleteProduct(id);
            return ResponseEntity.ok(ResponseObject.createResponse("Product with ID " + id + " deleted successfully!", null, 204));

    }

    @Operation(summary = "Update a product", description = "Update a product from the DB")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductBody productBody) {
        Product updatedProduct = productService.updateProduct(id, productBody);
        return ResponseEntity.ok(ResponseObject.createResponse("Product updated successfully!", updatedProduct, 200));
    }

    @Operation(summary = "Fetch all products", description = "Fetch all products in the Product Tree")
    @GetMapping("/tree")
    public ResponseEntity<?> getSortedFromTree() {
        List<Product> products = productService.getSortedDataFromTree();
        return ResponseEntity.ok(ResponseObject.createResponse("Products fetched successfully from the tree!", products, 200));
    }

    @Operation(summary = "Delete a product", description = "Delete a product from the Product Tree and DB")
    @DeleteMapping("/tree/{name}")
    public ResponseEntity<?> deleteProductFromTree(@PathVariable String name) {
        productService.deleteProductByName(name);
        return ResponseEntity.ok(ResponseObject.createResponse("Product '" + name + "' deleted successfully!", null, 204));
    }

    @Operation(summary = "Fetch a product", description = "Fetch a single product from the Product tree")
    @GetMapping("/tree/{name}")
    public ResponseEntity<?> getProductFromTree(@PathVariable String name) {
        Product product = productService.getProductFromTree(name);
        return ResponseEntity.ok(ResponseObject.createResponse("Product fetched successfully from the tree!", product, 200));
    }

    @Operation(summary = "Fetch category product", description = "Fetch all products in a specified category")
    @GetMapping("/category/{name}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String name) {
        List<Product> products = productService.getProductByCategory(name);
        return ResponseEntity.ok(ResponseObject.createResponse("Products fetched successfully from the category!", products, 200));
    }
}
