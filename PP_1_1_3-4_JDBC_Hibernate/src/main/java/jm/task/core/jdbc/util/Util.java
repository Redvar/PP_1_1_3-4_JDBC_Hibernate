package jm.task.core.jdbc.util;

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

    public static Connection createNewConnection() throws SQLException {
            Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            connection = con;
            System.out.println("Соединение установлено");
            return connection;
    }
}
