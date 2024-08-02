package cz.bublinoid.products;

public class ProductBuilder {
    private int id;
    private String title;
    private String description;
    private double cost;
    private double weight;
    private double width;
    private double length;
    private double height;

    public ProductBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public ProductBuilder setWidth(double width) {
        this.width = width;
        return this;
    }

    public ProductBuilder setLength(double length) {
        this.length = length;
        return this;
    }

    public ProductBuilder setHeight(double height) {
        this.height = height;
        return this;
    }

    public Product build() {
        return new Product(id, title, description, cost, weight, width, length, height);
    }
}
