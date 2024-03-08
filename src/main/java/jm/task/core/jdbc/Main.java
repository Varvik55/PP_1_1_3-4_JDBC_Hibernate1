package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService usDB = new UserServiceImpl();

//        Создание таблицы User(ов);
        usDB.createUsersTable();

//        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль (User с именем — name добавлен в базу данных)
        usDB.saveUser("Kirill","Gribov", (byte) 24);
        usDB.saveUser("Kirill1","Gribov", (byte) 24);
        usDB.saveUser("Kirill2","Gribov", (byte) 24);
        usDB.saveUser("Kirill3","Gribov", (byte) 24);

//        Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        usDB.getAllUsers().stream().forEach(System.out::println);

//        Очистка таблицы User(ов)
        usDB.cleanUsersTable();
        usDB.getAllUsers().stream().forEach(System.out::println);

//        Удаление таблицы
        usDB.dropUsersTable();

    }
}
