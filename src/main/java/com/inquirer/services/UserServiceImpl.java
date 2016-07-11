package com.inquirer.services;

import com.inquirer.dao.UserDao;
import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public List<User> getUsers() throws SQLException {
        return userDao.getUsers();
    }

    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
