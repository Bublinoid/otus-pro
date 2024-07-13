package ru.bublinoid;

import ru.bublinoid.config.AppConfig;
import ru.bublinoid.model.Product;
import ru.bublinoid.repository.ProductRepository;
import ru.bublinoid.service.Cart;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Commands: add [id], remove [id], show, exit");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            } else if (command.startsWith("add")) {
                int id;
                try {
                    id = Integer.parseInt(command.split(" ")[1]);
                    if (id < 1 || id > 10) {
                        throw new IllegalArgumentException("Invalid product ID. Valid IDs are 1 to 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                Product product = productRepository.getProductById(id);
                if (product != null) {
                    cart.addProduct(product);
                    System.out.println("Added: " + product);
                } else {
                    System.out.println("Product with ID " + id + " not found.");
                }
            } else if (command.startsWith("remove")) {
                int id;
                try {
                    id = Integer.parseInt(command.split(" ")[1]);
                    if (id < 1 || id > 10) {
                        throw new IllegalArgumentException("Invalid product ID. Valid IDs are 1 to 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                cart.removeProduct(id);
                System.out.println("Removed product with ID: " + id);
            } else if (command.equals("show")) {
                System.out.println("Cart: " + cart.getProducts());
            } else {
                System.out.println("Invalid command.");
            }
        }

        context.close();
    }
}
