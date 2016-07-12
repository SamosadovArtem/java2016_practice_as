package com.inquirer.services;

import com.inquirer.dao.UserDaoImpl;
import com.inquirer.models.User;

import java.sql.SQLException;

public class UserLoginService {

    public boolean CheckUser(User user)  {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoImpl());
        User tempUser = null;

        try {
            tempUser = userService.getUserByName(user.getName());
            userService.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempUser!=null;
    }
}
