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

    private static final String SELECT_ALL_USER_QUERY = "SELECT * FROM user";
    private static final String SELECT_ALL_QUESTION_QUERY = "SELECT * FROM question";
    private static final String SELECT_ANSWER_FOR_QUESTION_QUERY = "SELECT * FROM answer WHERE question = ?";
    private static final String ADD_USER_QUERY = "INSERT INTO user(name,age) VALUES (?,?)";

    private static final Logger loger = Logger.getLogger(QuestionDaoImpl.class);

    private String username;
    private String password;
    private String url;
    private Properties property;

    public QuestionDaoImpl(){
        loadProperties();
        loadDriver();
    }

    private PreparedStatement getStatement(String query) throws SQLException {
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = con.prepareStatement(query);
        return statement;
    }

    private void loadProperties(){
        property = new Properties();
        try(InputStream propertiesFile = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            property.load(propertiesFile);

            username = property.getProperty("db.user");
            password = property.getProperty("db.password");
            url = property.getProperty("db.url");
        } catch (FileNotFoundException e) {
            loger.error(e);
        } catch (IOException e) {
            loger.error(e);
        }
    }

    private void loadDriver(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e){
            loger.error(e);
        }
    }

    public void addUser(User user) throws SQLException {
        try (PreparedStatement statement = getStatement(ADD_USER_QUERY)) {
            statement.setString(1,user.getName());
            statement.setInt(2,user.getAge());
            statement.execute();
        } catch (SQLException e){
            loger.error(e);
        }

    }

    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        List<Answer> answers =  new ArrayList();

        try (PreparedStatement statement = getStatement(SELECT_ANSWER_FOR_QUESTION_QUERY)) {
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
            loger.error(e);
        }

        return answers;
    }

    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList();

        try (PreparedStatement statement = getStatement(SELECT_ALL_QUESTION_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Question tempQuestion = new Question();
                tempQuestion.setId(resultSet.getInt("id"));
                tempQuestion.setTitle(resultSet.getString("title"));
                questions.add(tempQuestion);
            }
        } catch (SQLException e){
            loger.error(e);
        }

        return questions;
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList();

        try (PreparedStatement statement = getStatement(SELECT_ALL_USER_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User tempUser = new User();
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setAge(resultSet.getInt("age"));
                users.add(tempUser);
            }
        } catch (SQLException e){
            loger.error(e);
        }

        return users;
    }
}
