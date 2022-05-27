package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
                    //Hibernate
        List<User> userList = new ArrayList<>();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("1vel", "vanov", (byte) 29);
        userService.saveUser("2Pavl", "Ianov", (byte) 39);
        userService.saveUser("3Pvel", "Ivnov", (byte) 49);
        userService.saveUser("4Pav", "Ivano", (byte) 59);
        userService.saveUser("5Pav", "Ivano", (byte) 59);
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();


                    // JDBC
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList = new ArrayList<>();

        userService.createUsersTable();
        userService.saveUser("Pavel", "Ivanov", (byte) 19);
        userService.saveUser("Pavel", "Ivanov", (byte) 19);
        userService.saveUser("Pavel", "Ivanov", (byte) 19);
        userService.saveUser("Pavel", "Ivanov", (byte) 19);
        userList = userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();


    }
}
