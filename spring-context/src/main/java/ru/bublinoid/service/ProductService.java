package ru.bublinoid.service;

import ru.bublinoid.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void removeProduct(int productId);
    List<Product> getProducts();
}
