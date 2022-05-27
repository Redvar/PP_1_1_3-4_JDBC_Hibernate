package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static jm.task.core.jdbc.util.Util.createNewConnection;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/users";
    private final static String LOGIN = "root";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String PASSWORD = "1234";
    private static Connection connection;

    public static void createNewConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        System.out.println("Соединение установлено");
    }

    public static Connection getConnection(){
        if (connection == null) {
            try {
                createNewConnection();
            } catch (SQLException e) {

            }
        }
        return connection;
    }
    
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }

    public static SessionFactory createHibernateSession() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", JDBC_DRIVER);
        settings.put("dialect", "org.hibernate.dialect.MySQL5Dialect");
        settings.put("hibernate.connection.url", URL);
        settings.put("hibernate.connection.username", LOGIN);
        settings.put("hibernate.connection.password", PASSWORD);
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.default_schema", "users");
        //Создание БД
        settings.put("format_sql", "true");
        settings.put("hibernate.hbm2ddl", "create");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }
}
