package ru.bublinoid.springboot1.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String title;
    private Double price;
}
