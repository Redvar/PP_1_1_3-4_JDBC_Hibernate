package jm.task.core.jdbc.util;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL = "jdbc:mysql://localhost:3306/users";
    private final static String LOGIN = "root";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String PASSWORD = "root";
    private static Connection connection;
    static Session session = null;


    public static Connection createNewConnection() throws SQLException {
            Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            connection = con;
            System.out.println("Соединение установлено");
            return connection;
    }

    public static Session createHibernateSession() {
        SessionFactory sessionFactory = null;
        ServiceRegistry serviceRegistry = null;
        return null;
    }
}
