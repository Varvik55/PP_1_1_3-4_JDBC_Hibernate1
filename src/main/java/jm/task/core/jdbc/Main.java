package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService usDB = new UserServiceImpl();


        usDB.createUsersTable();


        usDB.saveUser("Kirill","Gribov", (byte) 24);
        usDB.saveUser("Kirill1","Gribov", (byte) 24);
        usDB.saveUser("Kirill2","Gribov", (byte) 24);
        usDB.saveUser("Kirill3","Gribov", (byte) 24);


       usDB.getAllUsers().stream().forEach(System.out::println);

        usDB.removeUserById(4);

        usDB.getAllUsers().stream().forEach(System.out::println);


        // реализуйте алгоритм здесь
    }
}
