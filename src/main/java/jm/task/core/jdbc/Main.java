package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Kirill1","gribov", (byte) 23);
        us.saveUser("Kirill2","gribov", (byte) 23);
        us.saveUser("Kirill3","gribov", (byte) 23);
        us.saveUser("Kirill4","gribov", (byte) 23);
        us.getAllUsers().stream().forEach(System.out::println);
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
