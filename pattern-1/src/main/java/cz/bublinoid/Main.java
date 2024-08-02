package cz.bublinoid;

import cz.bublinoid.box.Box;
import cz.bublinoid.products.Product;
import cz.bublinoid.products.ProductBuilder;

public class Main {
    public static void main(String[] args) {
        Product product = new ProductBuilder()
                .setId(1)
                .setTitle("Laptop")
                .setDescription("Laptop for coding")
                .setCost(1500.0)
                .setWeight(1.2)
                .setWidth(15.0)
                .setLength(25.0)
                .setHeight(2.0)
                .build();

        System.out.println("Product ID: " + product.getId());
        System.out.println("Title: " + product.getTitle());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Cost: " + product.getCost());
        System.out.println("Weight: " + product.getWeight());
        System.out.println("Dimensions (WxLxH): " + product.getWidth() + "x" + product.getLength() + "x" + product.getHeight());

        Box box = new Box();
        box.addToList1("First line of the first list");
        box.addToList2("First line of second list");
        box.addToList3("First line of the third list");
        box.addToList4("First line of the fourth list");

        for (String item : box) {
            System.out.println(item);
        }
    }
}
