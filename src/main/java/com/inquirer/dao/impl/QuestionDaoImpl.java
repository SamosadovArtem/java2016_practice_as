package com.inquirer.dao.impl;

import com.inquirer.dao.QuestionDao;
import com.inquirer.models.Question;
import com.inquirer.utils.DaoHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private static final String SELECT_ALL_QUESTION_QUERY = "SELECT * FROM question";

    private static final Logger LOGER = Logger.getLogger(QuestionDaoImpl.class);

    private DaoHelper daoHelper = new DaoHelper();


    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList();

        try (PreparedStatement statement = daoHelper.getStatement(SELECT_ALL_QUESTION_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Question tempQuestion = new Question();
                tempQuestion.setId(resultSet.getInt("id"));
                tempQuestion.setTitle(resultSet.getString("title"));
                questions.add(tempQuestion);
            }
        } catch (SQLException e){
            LOGER.error(e);
        }

        return questions;
    }

}
