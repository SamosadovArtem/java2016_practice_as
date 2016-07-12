package com.inquirer.services;

import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    public List<User> getUsers() throws SQLException;

    public void addUser (User user) throws SQLException;

    public User getUserByName(String name) throws  SQLException;
}
