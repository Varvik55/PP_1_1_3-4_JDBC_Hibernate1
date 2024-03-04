package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService usDB = new UserServiceImpl();

        try {
            usDB.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        usDB.saveUser("Kirill","Gribov", (byte) 24);
        usDB.saveUser("Kirill1","Gribov", (byte) 24);
        usDB.saveUser("Kirill2","Gribov", (byte) 24);
        usDB.saveUser("Kirill3","Gribov", (byte) 24);


       List<User> l = usDB.getAllUsers();
        System.out.println(l);
        usDB.removeUserById(4);

        l = usDB.getAllUsers();
        System.out.println(l);

        // реализуйте алгоритм здесь
    }
}
