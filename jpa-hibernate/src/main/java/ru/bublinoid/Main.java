package ru.bublinoid;

import ru.bublinoid.dao.CustomerDAO;
import ru.bublinoid.dao.ProductDAO;
import ru.bublinoid.model.Customer;
import ru.bublinoid.model.Product;
import ru.bublinoid.util.JPAUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static CustomerDAO customerDAO = new CustomerDAO();
    private static ProductDAO productDAO = new ProductDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeDatabase();
        try {
            while (true) {
                printMenu();
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewCustomerProducts();
                        break;
                    case 2:
                        viewProductCustomers();
                        break;
                    case 3:
                        deleteCustomer();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 0:
                        JPAUtil.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            JPAUtil.close();
        }
    }

    private static void printMenu() {
        System.out.println("1. View products purchased by customer");
        System.out.println("2. View customers who bought a product");
        System.out.println("3. Delete customer");
        System.out.println("4. Delete product");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void viewCustomerProducts() {
        System.out.print("Enter customer ID: ");
        Long customerId = Long.parseLong(scanner.nextLine());
        Customer customer = customerDAO.findById(customerId);

        if (customer != null) {
            List<Product> products = productDAO.findProductsByCustomer(customer);
            System.out.println("Products purchased by " + customer.getName() + ":");
            products.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void viewProductCustomers() {
        System.out.print("Enter product ID: ");
        Long productId = Long.parseLong(scanner.nextLine());
        Product product = productDAO.findById(productId);

        if (product != null) {
            List<Customer> customers = customerDAO.findCustomersByProduct(product);
            System.out.println("Customers who bought " + product.getName() + ":");
            customers.forEach(customer -> System.out.println(customer.getName()));
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteCustomer() {
        System.out.print("Enter customer ID: ");
        Long customerId = Long.parseLong(scanner.nextLine());
        Customer customer = customerDAO.findById(customerId);

        if (customer != null) {
            customerDAO.delete(customer);
            System.out.println("Customer deleted.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID: ");
        Long productId = Long.parseLong(scanner.nextLine());
        Product product = productDAO.findById(productId);

        if (product != null) {
            productDAO.delete(product);
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void initializeDatabase() {
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setPrice(1200.00);

        Product product2 = new Product();
        product2.setName("Smartphone");
        product2.setPrice(800.00);

        productDAO.save(product1);
        productDAO.save(product2);

        Customer customer1 = new Customer();
        customer1.setName("Alex");

        Customer customer2 = new Customer();
        customer2.setName("Eva");

        customer1.getProducts().add(product1);
        customer1.getProducts().add(product2);
        customer2.getProducts().add(product1);

        customerDAO.save(customer1);
        customerDAO.save(customer2);

        System.out.println("Database initialized with sample data.");
    }
}
