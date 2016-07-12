package com.inquirer.services.impl;

import com.inquirer.dao.UserDao;
import com.inquirer.dao.impl.UserDaoImpl;
import com.inquirer.models.User;
import com.inquirer.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> getUsers() throws SQLException {
        return userDao.getUsers();
    }

    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        return userDao.getUserByName(name);
    }

    public boolean isUserExist(User user)  {
        User tempUser = null;

        try {
            tempUser = userDao.getUserByName(user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempUser!=null;
    }

}
