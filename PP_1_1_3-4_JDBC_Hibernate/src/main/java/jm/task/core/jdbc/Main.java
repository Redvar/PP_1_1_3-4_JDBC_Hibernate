package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.createNewConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
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


    }
}
