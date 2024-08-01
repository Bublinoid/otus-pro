package ru.bublinoid;

import ru.bublinoid.config.AppConfig;
import ru.bublinoid.model.Product;
import ru.bublinoid.repository.ProductRepository;
import ru.bublinoid.service.ProductServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
             Scanner scanner = new Scanner(System.in)) {
            ProductRepository productRepository = context.getBean(ProductRepository.class);
            ProductServiceImpl cart = context.getBean(ProductServiceImpl.class);

            while (true) {
                System.out.println("Commands: add [id], remove [id], show, exit");
                String command = scanner.nextLine();
                String[] commandParts = command.split(" ");
                String action = commandParts[0];

                switch (action) {
                    case "exit":
                        return;
                    case "add":
                        try {
                            int id = parseId(commandParts, "add");
                            Product product = productRepository.getProductById(id);
                            if (product != null) {
                                cart.addProduct(product);
                                System.out.println("Added: " + product);
                            } else {
                                System.out.println("Product with ID " + id + " not found.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "remove":
                        try {
                            int id = parseId(commandParts, "remove");
                            cart.removeProduct(id);
                            System.out.println("Removed product with ID: " + id);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "show":
                        System.out.println("Cart: " + cart.getProducts());
                        break;
                    default:
                        System.out.println("Invalid command.");
                        break;
                }
            }
        }
    }

    private static int parseId(String[] commandParts, String operation) {
        if (commandParts.length < 2) {
            throw new IllegalArgumentException("Invalid command format for " + operation + ". Please provide an ID.");
        }
        try {
            int id = Integer.parseInt(commandParts[1]);
            if (id < 1 || id > 10) {
                throw new IllegalArgumentException("Invalid product ID. Valid IDs are 1 to 10.");
            }
            return id;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for " + operation + ". Please enter a valid number.");
        }
    }
}
