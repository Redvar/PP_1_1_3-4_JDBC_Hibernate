package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS users(\n" +
                "    id int not null primary key AUTO_INCREMENT,\n" +
                "    name varchar(30),\n" +
                "    lastName varchar(30),\n" +
                "    age smallint check (age>0))";
        try (Session session = Util.createHibernateSession().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(sqlQuery);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {

        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.createHibernateSession().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("drop table users");
            query.executeUpdate();
            session.flush();
        } catch (HibernateException e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.createHibernateSession().openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setAge(age);
            user.setLastName(lastName);
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {

        }


    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.createHibernateSession().openSession()) {
            session.beginTransaction();
            String hql = "DELETE User WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (HibernateException e) {

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        try (Session session = Util.createHibernateSession().openSession()) {
            session.beginTransaction();
            userList = session.createQuery("SELECT a FROM User a", User.class).getResultList();
            return userList;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        try (Session session = Util.createHibernateSession().openSession()) {
            session.getTransaction().begin();
            String hql = "delete from User";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {

        }
    }
}
