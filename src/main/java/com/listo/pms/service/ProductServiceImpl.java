package com.listo.pms.service;

import com.listo.pms.dto.ProductBody;
import com.listo.pms.model.Category;
import com.listo.pms.model.Product;
import com.listo.pms.repository.ProductRepository;
import com.listo.pms.tree.ProductBinaryTree;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    private final ProductBinaryTree productTree;

    @Autowired
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productTree = new ProductBinaryTree();
    }

    @PostConstruct
    public void initializeTreeFromDatabase() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            productTree.addProduct(product);
        }
        System.out.println("Binary tree prepopulated: ");
        productTree.inOrder();

    }

    public Product saveProduct(ProductBody productBody){
        String name = productBody.getName();
        int categoryId = productBody.getCategoryId();
        Product newProduct = new Product();
        newProduct.setName(name);
        Category category = categoryService.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        newProduct.setCategory(category);

        productTree.addProduct(newProduct);
        return productRepository.save(newProduct);
    }

    public List<Product> getProducts(){
        return productRepository.findAllWithCategory();
    }

    public Optional<Product> getProduct(int id){
        return productRepository.findById(id);
    }

    public void deleteProduct(int id){
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        productRepository.deleteById(id);
    }

    public void deleteProductByName(String name){
        Product product = productTree.searchProduct(name);
        if (product != null) {
            productTree.deleteProduct(name);
            productRepository.deleteById(product.getId());
        }

        if (!productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product not found with name: " + name);
        }
        productRepository.deleteById(product.getId());
    }

    public Product getProductFromTree(String name){
        return productTree.searchProduct(name);
    }

    public Product updateProduct(int id, ProductBody productBody){
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        oldProduct.setName(productBody.getName());
        return productRepository.save(oldProduct);
    }

    public Page<Product> getSortedProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    public List<Product> getSortedDataFromTree(){
        return productTree.inOrder();
    }

    public List<Product> getProductByCategory(String name){
        return productRepository.findByCategoryName(name);
    }
}
