package com.inquirer.dao;

import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public void addUser(User user) throws SQLException;

    public List<User> getUsers() throws SQLException;
}
