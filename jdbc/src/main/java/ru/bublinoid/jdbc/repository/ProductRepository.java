package ru.bublinoid.jdbc.repository;

import ru.bublinoid.jdbc.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
