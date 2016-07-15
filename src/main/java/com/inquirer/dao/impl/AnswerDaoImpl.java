package com.inquirer.dao.impl;


import com.inquirer.dao.AnswerDao;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.utils.DaoHelper;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private static final String SELECT_ANSWER_FOR_QUESTION_QUERY = "SELECT * FROM answer WHERE question = ?";
    private static final String SELECT_ANSWER_BY_ID_QUERY = "SELECT * FROM answer WHERE answer.id = ?";

    private static final Logger LOGER = Logger.getLogger(AnswerDaoImpl.class);

    private DaoHelper daoHelper = new DaoHelper();

    @Override
    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        List<Answer> answers =  new ArrayList();

        try (PreparedStatement statement = daoHelper.getStatement(SELECT_ANSWER_FOR_QUESTION_QUERY)) {
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
        } finally {
            daoHelper.closeConnection();
        }

        return answers;
    }

    @Override
    public Answer getAnswerById(int id) throws SQLException {
        Answer answer = new Answer();

        try (PreparedStatement statement = daoHelper.getStatement(SELECT_ANSWER_BY_ID_QUERY)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setTitle(resultSet.getString("title"));
                answer.setQuestion(resultSet.getInt("question"));
                answer.setRight(resultSet.getBoolean("isRight"));
            }
        } catch (SQLException e) {
            LOGER.error(e);
        } finally {
            daoHelper.closeConnection();
        }
        return answer;
    }
}
