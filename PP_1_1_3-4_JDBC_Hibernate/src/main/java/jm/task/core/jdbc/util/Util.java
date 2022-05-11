package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/users";
    private final static String LOGIN = "root";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String PASSWORD = "qffq32@@EEFWQWFfrfed";
    private static Connection connection;


    public static Connection createNewConnection() throws SQLException {
            Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            connection = con;
            System.out.println("Соединение установлено");
            return connection;
    }

    public static Session createHibernateSession() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", JDBC_DRIVER);
        settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
        settings.put("hibernate.connection.url", URL);
        settings.put("hibernate.connection.username", LOGIN);
        settings.put("hibernate.connection.password", PASSWORD);
        settings.put("hibernate.current_session_context_class", "thread");
        //Создание БД

        settings.put("hibernate.hbm2ddl", "create");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
