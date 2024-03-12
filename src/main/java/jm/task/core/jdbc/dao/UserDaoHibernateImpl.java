package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sf = Util.getSessionFactory();
    private Transaction transaction = null;
    private String tablName = "users";
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + tablName + " ("
                + "id BIGINT not null AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastname VARCHAR(255),"
                + "age BIGINT"
                + ")";

        try (Session session = sf.openSession();){
            transaction = session.beginTransaction();
            session.createNativeQuery(createTable, User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sf.openSession();){
            transaction = session.beginTransaction();
            String dropUser = "DROP TABLE IF EXISTS users";
            session.createNativeQuery(dropUser).executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge((long) age);

        try(Session session = sf.openSession();) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        try ( Session session = sf.openSession();){
            transaction = session.beginTransaction();
            User user = session.get(User.class,id);
            session.delete(user);
            transaction.commit();
        } catch (Exception a) {
            if (transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;

        try (Session session = sf.openSession();){
            transaction = session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sf.openSession();){
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e ){
            if(transaction != null){
                transaction.rollback();
            }
        }

    }
}
