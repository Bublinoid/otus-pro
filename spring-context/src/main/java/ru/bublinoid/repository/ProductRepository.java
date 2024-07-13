package ru.bublinoid.repository;

import ru.bublinoid.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product(1, "Product1", 10.0));
        products.add(new Product(2, "Product2", 20.0));
        products.add(new Product(3, "Product3", 30.0));
        products.add(new Product(4, "Product4", 40.0));
        products.add(new Product(5, "Product5", 50.0));
        products.add(new Product(6, "Product6", 60.0));
        products.add(new Product(7, "Product7", 70.0));
        products.add(new Product(8, "Product8", 80.0));
        products.add(new Product(9, "Product9", 90.0));
        products.add(new Product(10, "Product10", 100.0));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
