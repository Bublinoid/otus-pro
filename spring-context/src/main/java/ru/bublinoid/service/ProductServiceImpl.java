package ru.bublinoid.service;

import ru.bublinoid.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    @Override
    public void removeProduct(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "ProductServiceImpl{" +
                "products=" + products +
                '}';
    }
}
