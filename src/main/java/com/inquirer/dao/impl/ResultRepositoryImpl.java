package com.inquirer.dao.impl;

import com.inquirer.dao.ResultRepository;
import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import com.inquirer.utils.DaoHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ResultRepositoryImpl implements ResultRepository {

    private static final String GET_LAST_USER_RESULT_QUERY = "SELECT * FROM result WHERE user = ? " +
            "ORDER BY id DESC LIMIT 1";
    private static final String ADD_RESULT_QUERY = "INSERT INTO RESULT(user, mark) VALUES (?,?)";
    private static final Logger LOGER = Logger.getLogger(QuestionRepositoryImpl.class);

    private DaoHelper daoHelper = new DaoHelper();

    @Override
    public Result getLastUserResult(User user) throws UserWithoutMarkExeption{
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
        finally {
            daoHelper.closeConnection();
        }

        if (result==null){
            throw new UserWithoutMarkExeption("User have no marks");
        }

        return result;
    }

    @Override
    public void addUserResult(Result result) {
        try (PreparedStatement statement = daoHelper.getStatement(ADD_RESULT_QUERY)) {
            statement.setInt(1,result.getUser());
            statement.setInt(2,result.getMark());
            statement.execute();
        } catch (SQLException e){
            LOGER.error(e);
        } finally {
            daoHelper.closeConnection();
        }
    }
}
