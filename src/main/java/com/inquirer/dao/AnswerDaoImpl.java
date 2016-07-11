package com.inquirer.dao;


import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private static final String SELECT_ANSWER_FOR_QUESTION_QUERY = "SELECT * FROM answer WHERE question = ?";

    private static final Logger LOGER = Logger.getLogger(AnswerDaoImpl.class);

    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    @Override
    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        List<Answer> answers =  new ArrayList();

        try (PreparedStatement statement = propertiesLoader.getStatement(SELECT_ANSWER_FOR_QUESTION_QUERY)) {
            statement.setInt(1,question.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Answer tempAnswer = new Answer();
                tempAnswer.setId(resultSet.getInt("id"));
                tempAnswer.setTitle(resultSet.getString("title"));
                tempAnswer.setQuestion(resultSet.getInt("question"));
                tempAnswer.setRight(resultSet.getBoolean("isRight"));
                answers.add(tempAnswer);
            }
        } catch (SQLException e){
            LOGER.error(e);
        }

        return answers;
    }
}
