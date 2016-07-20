package com.inquirer.services.impl;

import com.inquirer.dao.UserDao;
import com.inquirer.dao.impl.UserRepository;
import com.inquirer.models.User;
import com.inquirer.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired private UserDao userDao;

    @Override
    public List<User> getUsers() throws SQLException {
        return userDao.getUsers();
    }

    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    @Override
    public User getUserByName(String name) {
        try {
            return userDao.getUserByName(name);
        } catch (SQLException e) {
            LOGER.error(e);
        }
        return null;
    }

    public boolean isUserExist(User user)  {
        User tempUser = null;

        try {
            tempUser = userDao.getUserByName(user.getName());
        } catch (SQLException e) {
            LOGER.error(e);
        }
        return tempUser != null;
    }

}
