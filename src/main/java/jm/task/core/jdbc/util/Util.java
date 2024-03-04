package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String HOST = "jdbc:mysql://localhost:3306/TestDataBase";
    private final String USER = "root";
    private final String PASSWORD = "12345678";
    private final Connection connection;

    public Util() throws SQLException {
            connection = DriverManager.getConnection(HOST,USER,PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}
