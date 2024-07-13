package ru.bublinoid.service;

import ru.bublinoid.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class Cart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public void removeProduct(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }
}
