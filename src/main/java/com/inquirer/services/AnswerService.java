package com.inquirer.services;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public interface AnswerService {

    List<Answer> getAnswersForQuestion(Question question) throws SQLException;
    Answer getAnswerById(int id) throws SQLException;
    void setUserAnswer(int id);
    int getTestResult();
    int getUserAnswerIdByQuestionNumber(int number);
    void clearUserAnswers();
    int getUserAnswersAmount();
}
