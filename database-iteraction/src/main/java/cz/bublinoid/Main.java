package cz.bublinoid;

import cz.bublinoid.database.DatabaseManager;
import cz.bublinoid.model.Product;
import cz.bublinoid.processor.AnnotationProcessor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            String dropTableSQL = AnnotationProcessor.dropTable(Product.class);
            System.out.println("Drop Table SQL: " + dropTableSQL);
            DatabaseManager.executeStatement(dropTableSQL);
            System.out.println("Table dropped successfully (if existed).");

            String createTableSQL = AnnotationProcessor.createTable(Product.class);
            System.out.println("Create Table SQL: " + createTableSQL);
            DatabaseManager.executeStatement(createTableSQL);
            System.out.println("Table created successfully.");

            Product product = new Product(1, "Example Product", 19.99);
            String insertSQL = AnnotationProcessor.insertData(Product.class);
            System.out.println("Insert SQL: " + insertSQL);
            DatabaseManager.executePreparedStatement(insertSQL, product.getId(), product.getTitle(), product.getPrice());
            System.out.println("Data inserted successfully.");

            String selectSQL = AnnotationProcessor.selectData(Product.class);
            System.out.println("Select SQL: " + selectSQL);
            List<Product> products = DatabaseManager.executeSelectStatement(selectSQL);
            for (Product p : products) {
                System.out.println(p.getId() + " - " + p.getTitle() + " - $" + p.getPrice());
            }

            product.setPrice(99.99);
            String updateSQL = AnnotationProcessor.updateData(Product.class);
            System.out.println("Update SQL: " + updateSQL);
            DatabaseManager.executePreparedStatement(updateSQL, product.getId(), product.getTitle(), product.getPrice(), product.getId());
            System.out.println("Data updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
