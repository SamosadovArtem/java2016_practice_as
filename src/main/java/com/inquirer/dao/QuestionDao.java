package com.inquirer.dao;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDao {

    public List<Question> getQuestions() throws SQLException;
    public Question getQuestionByNumber(int number) throws SQLException;
}
