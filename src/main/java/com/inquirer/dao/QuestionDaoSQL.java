package com.inquirer.dao;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.models.User;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoSQL implements QuestionDao {

    private static String username = "root";
    private static String password = "admin";
    private static String url = "jdbc:mysql://localhost:3306/inquirer";

    private void loadDriver(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addUser(User user) throws SQLException {
        loadDriver();

        try (Connection con = DriverManager.getConnection(url,username,password);
            Statement statement = con.createStatement()) {
            statement.execute("insert into user(name,age) values ('"+user.getName()+"',"+user.getAge()+") ");
        }

        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        List<Answer> answers =  new ArrayList<Answer>();

        loadDriver();

        try (Connection con = DriverManager.getConnection(url,username,password);
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM answer " +
                    "WHERE question=" + question.getId())) {
            while (resultSet.next()) {
                Answer tempAnswer = new Answer();
                tempAnswer.setId(resultSet.getInt("id"));
                tempAnswer.setTitle(resultSet.getString("title"));
                tempAnswer.setQuestion(resultSet.getInt("question"));
                tempAnswer.setRight(resultSet.getBoolean("isRight"));
                answers.add(tempAnswer);
            }
        }

        catch (SQLException e){
            e.printStackTrace();
        }

        return answers;
    }

    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList<Question>();

        loadDriver();

        try (Connection con = DriverManager.getConnection(url,username,password);
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM question")) {

            while (resultSet.next()) {
                Question tempQuestion = new Question();
                tempQuestion.setId(resultSet.getInt("id"));
                tempQuestion.setTitle(resultSet.getString("title"));
                questions.add(tempQuestion);
            }
        }

        catch (SQLException e){
            e.printStackTrace();
        }

        return questions;
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<User>();

        loadDriver();

        try (Connection con = DriverManager.getConnection(url,username,password);
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {

            while (resultSet.next()) {
                User tempUser = new User();
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setAge(resultSet.getInt("age"));
                users.add(tempUser);
            }
        }

        catch (SQLException e){
            e.printStackTrace();
        }


        return users;
    }
}
