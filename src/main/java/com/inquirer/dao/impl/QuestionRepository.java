package com.inquirer.dao.impl;

import com.inquirer.dao.QuestionDao;
import com.inquirer.models.Question;
import com.inquirer.utils.DaoHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository implements QuestionDao {

    private static final String SELECT_ALL_QUESTION_QUERY = "SELECT * FROM question";
    private static final String SELECT_QUESTION_BY_NUMBER_QUERY = "SELECT * FROM question WHERE id = ?";

    private static final Logger LOGER = Logger.getLogger(QuestionRepository.class);

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
        } finally {
            daoHelper.closeConnection();
        }

        return questions;
    }

    @Override
    public Question getQuestionByNumber(int number) throws SQLException {
        Question question = new Question();

        try (PreparedStatement statement = daoHelper.getStatement(SELECT_QUESTION_BY_NUMBER_QUERY)) {
            statement.setInt(1, number);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setTitle(resultSet.getString("title"));
            }
        } catch (SQLException e){
            LOGER.error(e);
        } finally {
            daoHelper.closeConnection();
        }

        return question;
    }

}
