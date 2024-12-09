package com.listo.pms.service;

import com.listo.pms.dto.ProductBody;
import com.listo.pms.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product saveProduct(ProductBody productBody);

    public List<Product> getProducts();

    public Product getProduct(int id);

    public void deleteProduct(int id);

    public void deleteProductByName(String name);

    public Product getProductFromTree(String name);

    public Product updateProduct(int id, ProductBody productBody);

    public Page<Product> getSortedProducts(int page, int size, String sortBy, String sortDir);

    public List<Product> getSortedDataFromTree();

    public List<Product> getProductByCategory(String name);
}