package com.inquirer.services;

import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws SQLException;

    Question getQuestionByNumber(int number) throws SQLException;

    int getQuestionsAmount();
}
