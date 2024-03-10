package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private String tablName = "users";
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession();) {
            session.getTransaction().begin();
            String createTable = "CREATE TABLE IF NOT EXISTS " + tablName + " ("
                    + "id BIGINT not null AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    + "lastname VARCHAR(255),"
                    + "age BIGINT"
                    + ")";
            session.createNativeQuery(createTable, User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();){
            session.getTransaction().begin();
            String dropUser = "DROP TABLE IF EXISTS users";
            session.createNativeQuery(dropUser).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge((long) age);

        try(Session session = Util.getSessionFactory().openSession();) {
            Transaction tr = session.beginTransaction();
            session.persist(user);
            tr.commit();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSessionFactory().openSession();){
            session.getTransaction().begin();
            User user = session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list;
        try (Session session = Util.getSessionFactory().openSession();) {
            session.getTransaction().begin();

            list = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();){
            session.getTransaction().begin();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }

    }
}
