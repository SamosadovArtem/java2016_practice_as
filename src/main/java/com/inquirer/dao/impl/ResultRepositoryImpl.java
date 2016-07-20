package com.inquirer.dao.impl;

import com.inquirer.dao.ResultRepository;
import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.mapper.ResultMapper;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepositoryImpl implements ResultRepository {

    private static final String GET_LAST_USER_RESULT_QUERY = "SELECT * FROM result WHERE user = ? " +
            "ORDER BY id DESC LIMIT 1";
    private static final String ADD_RESULT_QUERY = "INSERT INTO RESULT(user, mark) VALUES (?,?)";
    private static final Logger LOGER = Logger.getLogger(QuestionRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result getLastUserResult(User user) throws UserWithoutMarkExeption{
        List<Result> results = results = jdbcTemplate.query(GET_LAST_USER_RESULT_QUERY, new Object[]{user.getId()},
                new ResultMapper());

        if (results.isEmpty()){
            throw new UserWithoutMarkExeption("User have no marks");
        }

        return results.get(0);
    }

    @Override
    public void addUserResult(Result result) {
        jdbcTemplate.update(ADD_RESULT_QUERY, result.getUser(), result.getMark());
    }
}
