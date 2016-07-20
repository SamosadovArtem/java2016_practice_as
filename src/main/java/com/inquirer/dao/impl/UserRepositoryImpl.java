package com.inquirer.dao.impl;

import com.inquirer.dao.UserRepository;
import com.inquirer.mapper.UserMapper;
import com.inquirer.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SELECT_ALL_USER_QUERY = "SELECT * FROM user";
    private static final String ADD_USER_QUERY = "INSERT INTO user(name,age) VALUES (?,?)";
    private static final String GET_USER_BY_NAME_QUERY = "SELECT * FROM user WHERE name = ?";

    private static final Logger LOGER = Logger.getLogger(QuestionRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user){
        jdbcTemplate.update(ADD_USER_QUERY,user.getName(),user.getAge());
    }

    @Override
    public List<User> getUsers(){
        List<User> users = jdbcTemplate.query(SELECT_ALL_USER_QUERY,new UserMapper());

        return users;
    }

    @Override
    public User getUserByName(String name){
        List<User> users = jdbcTemplate.query(GET_USER_BY_NAME_QUERY, new Object[]{name},
                new UserMapper());

        if (users.isEmpty()){
            return null;
        }

        return users.get(0);
    }
}
