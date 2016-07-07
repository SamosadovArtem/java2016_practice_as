package com.inquirer.dao;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDao {

    public void addUser (User user) throws SQLException;

    public List<Answer> getAnswersForQuestion(Question question) throws SQLException;

    public List<Question> getQuestions() throws SQLException;

    public List<User> getUsers() throws SQLException;

}
