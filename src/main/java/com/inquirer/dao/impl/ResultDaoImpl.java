package com.inquirer.dao.impl;

import com.inquirer.dao.ResultDao;
import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import com.inquirer.utils.DaoHelper;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDaoImpl implements ResultDao {

    private static final String GET_LAST_USER_RESULT_QUERY = "SELECT * FROM result WHERE user = ? " +
            "ORDER BY id DESC LIMIT 1";
    private static final Logger LOGER = Logger.getLogger(QuestionDaoImpl.class);

    private DaoHelper daoHelper = new DaoHelper();

    @Override
    public Result getLastUserResult(User user) throws UserWithoutMarkExeption {
        Result result = null;

        try (PreparedStatement statement = daoHelper.getStatement(GET_LAST_USER_RESULT_QUERY)) {
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = new Result();
                result.setId(resultSet.getInt("id"));
                result.setUser(resultSet.getInt("user"));
                result.setMark(resultSet.getInt("mark"));
            }
        } catch (SQLException e) {
            LOGER.error(e);
        }

        if (result==null){
            throw new UserWithoutMarkExeption("User have no marks");
        }

        return result;
    }
}
