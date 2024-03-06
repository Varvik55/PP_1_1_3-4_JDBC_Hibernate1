package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/TestDataBase";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";



    public static Connection getConnection() {
        Connection connection = null;
        try { connection = DriverManager.getConnection(HOST, USER, PASSWORD);
            System.out.println("Успешное подключение к бд");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
