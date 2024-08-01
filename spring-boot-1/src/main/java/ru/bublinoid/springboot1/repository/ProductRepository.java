package ru.bublinoid.springboot1.repository;

import ru.bublinoid.springboot1.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void save(Product product) {
        products.add(product);
    }
}
