package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:postgresql://serverok.vkozik.in.ua:5432/sofy_db";
    private static final String DB_USER = "sofy";
    private static final String DB_PASS = "*******";
    private static final Database database = new Database();

    private Database(){
    }

    public static Database getInstance() {
        return database;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
