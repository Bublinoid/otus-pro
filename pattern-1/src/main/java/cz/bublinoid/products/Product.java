package cz.bublinoid.products;

public class Product {
    private final int id;
    private final String title;
    private final String description;
    private final double cost;
    private final double weight;
    private final double width;
    private final double length;
    private final double height;

    public Product(int id, String title, String description, double cost, double weight, double width, double length, double height) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public double getWeight() {
        return weight;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }
}
