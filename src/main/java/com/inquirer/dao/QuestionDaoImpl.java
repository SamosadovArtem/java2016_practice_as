package com.inquirer.dao;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.models.User;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QuestionDaoImpl implements QuestionDao {

    private static final String SELECT_ALL_QUESTION_QUERY = "SELECT * FROM question";

    private static final Logger LOGER = Logger.getLogger(QuestionDaoImpl.class);

    private PropertiesLoader propertiesLoader = new PropertiesLoader();


    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList();

        try (PreparedStatement statement = propertiesLoader.getStatement(SELECT_ALL_QUESTION_QUERY);
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
