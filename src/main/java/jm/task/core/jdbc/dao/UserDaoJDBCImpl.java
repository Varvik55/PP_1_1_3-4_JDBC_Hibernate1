package jm.task.core.jdbc.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao  {
    Util util = null;
    {
        try {
            util = new Util();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {

    }



    public void createUsersTable()  {

        String tablName = "users";
        String createTable = "CREATE TABLE IF NOT EXISTS "+ tablName + " ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastname VARCHAR(255),"
                + "age BIGINT"
                + ")";
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(createTable);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropUser = "DROP TABLE IF EXISTS users";
        try (Statement statement = util.getConnection().createStatement();){

            statement.executeUpdate(dropUser);

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
        String remove = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(remove);){
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

       String getUser ="SELECT * FROM users";
        try (Statement statement = util.getConnection().createStatement();){
            ResultSet resultSet = statement.executeQuery(getUser);
            while (resultSet.next()){
                list.add(new User(resultSet.getLong("id")
                        , resultSet.getString("name")
                        , resultSet.getString("lastName")
                        , (byte) resultSet.getInt("age")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
