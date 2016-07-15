package com.inquirer.dao;

import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void addUser(User user) throws SQLException;

    List<User> getUsers() throws SQLException;

    User getUserByName(String name) throws  SQLException;
}
