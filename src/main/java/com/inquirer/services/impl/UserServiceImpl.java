package com.inquirer.services.impl;

import com.inquirer.dao.UserRepository;
import com.inquirer.models.User;
import com.inquirer.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGER = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getUsers() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) throws SQLException {
        userRepository.save(user);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    public boolean isUserExist(User user)  {
        User tempUser = userRepository.getUserByName(user.getName());
        return tempUser != null;
    }
}
