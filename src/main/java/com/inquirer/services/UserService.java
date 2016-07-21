package com.inquirer.services;

import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getUsers() throws SQLException;

    void addUser (User user)throws SQLException;

    User getUserByName(String name);

    boolean isUserExist(User user);

}
