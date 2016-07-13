package com.inquirer.services;

import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {

    public List<Question> getQuestions() throws SQLException;
    public Question getQuestionByNumber(int number) throws SQLException;

}
