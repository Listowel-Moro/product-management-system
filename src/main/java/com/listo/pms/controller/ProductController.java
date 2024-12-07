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
        try {
            Product savedProduct = productService.saveProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseObject.createResponse("Product added successfully!", savedProduct, 201));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseObject.createResponse("Error saving product: " + e.getMessage(), null, 500));
        }
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
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        return productService.getProduct(id)
                .map(product -> ResponseEntity.ok(ResponseObject.createResponse("Product fetched successfully!", product, 200)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseObject.createResponse("Product with ID " + id + " not found", null, 404)));
    }

    @Operation(summary = "Delete a product", description = "Delete a single product from the DB")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(ResponseObject.createResponse("Product with ID " + id + " deleted successfully!", null, 204));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseObject.createResponse("Error deleting product: " + e.getMessage(), null, 500));
        }
    }

    @Operation(summary = "Update a product", description = "Update a product from the DB")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductBody productBody) {
        try {
            Product updatedProduct = productService.updateProduct(id, productBody);
            return ResponseEntity.ok(ResponseObject.createResponse("Product updated successfully!", updatedProduct, 200));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseObject.createResponse("Error updating product: " + e.getMessage(), null, 500));
        }
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
        try {
            productService.deleteProductByName(name);
            return ResponseEntity.ok(ResponseObject.createResponse("Product '" + name + "' deleted successfully!", null, 204));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseObject.createResponse("Error deleting product: " + e.getMessage(), null, 500));
        }
    }

    @Operation(summary = "Fetch a product", description = "Fetch a single product from the Product tree")
    @GetMapping("/tree/{name}")
    public ResponseEntity<?> getProductFromTree(@PathVariable String name) {
        try {
            Product product = productService.getProductFromTree(name);
            return ResponseEntity.ok(ResponseObject.createResponse("Product fetched successfully from the tree!", product, 200));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseObject.createResponse("Product with name '" + name + "' not found", null, 404));
        }
    }
}
