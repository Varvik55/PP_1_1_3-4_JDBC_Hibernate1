package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    Util util;
    User user;
    {
        try {
            util = new Util();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//получить всех юзеров из таблици
    private final String getQwery = "select * from users";

    public void createUsersTable()  {
        String tablName = "users";

        final String createTableSQL = "CREATE TABLE IF NOT EXISTS "+ tablName + " ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastname VARCHAR(255),"
                + "age BIGINT"
                + ")";


        try (
             Statement statement = util.getConnection().createStatement()) {
                statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


    public void dropUsersTable() {
        String tableName = "Users";
        String dropTableSQL = "DROP TABLE IF EXISTS " + tableName;

        try (Statement statement = util.getConnection().createStatement();) {
            statement.executeUpdate(dropTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String nameTabl = "users";
        String insertSQL = "INSERT INTO "+nameTabl+" (name, lastname, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(insertSQL);){
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Данные успешно добавлены в таблицу.");

        } else{
            System.out.println("Не удалось добавить данные в таблицу.");
        }
        } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
    }

    public void removeUserById(long id) {
        String nameTabl = "users";
        String deleteSQL = "DELETE FROM "+nameTabl+" WHERE id = ?";

        try(PreparedStatement preparedStatement = util.getConnection().prepareStatement(deleteSQL)) {
            preparedStatement.setLong(1,id);

            int res = preparedStatement.executeUpdate();
            if (res > 0 ){
                System.out.println("Елемент " + id + "Успешно удален");
            } else {
                System.out.println("Ошибка");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List <User> list = new ArrayList<>();
           try ( Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getQwery);) {
               while (resultSet.next()) {
                   list.add(new User(resultSet.getLong("id")
                           , resultSet.getString("name")
                           , resultSet.getString("lastName")
                           , (byte) resultSet.getInt("age")));
               }

           } catch (SQLException s){
               s.printStackTrace();
           }
        return list;
    }

    public void cleanUsersTable() {
        String reqest ="TRUNCATE TABLE users";

        try (Statement statement = util.getConnection().createStatement()){
            statement.executeUpdate(reqest);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
