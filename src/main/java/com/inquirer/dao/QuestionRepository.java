package com.inquirer.dao;

import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {

    List<Question> getQuestions() throws SQLException;
    Question getQuestionByNumber(int number) throws SQLException;
}
