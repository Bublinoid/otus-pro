package cz.bublinoid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static DataSource instance;
    private Connection connection;

    private DataSource() {
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pattern-2", "postgres", "7117513Brat");
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the DB", e);
        }
    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
