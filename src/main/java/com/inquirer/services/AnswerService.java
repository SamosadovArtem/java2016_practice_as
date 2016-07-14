package com.inquirer.services;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public interface AnswerService {

    public List<Answer> getAnswersForQuestion(Question question) throws SQLException;
    public  Answer getAnswerById(int id) throws SQLException;
    public void setUserAnswer(int id);
    public int getTestResult();
    public int getUserAnswerIdByQuestionNumber(int number);
    public void clearUserAnswers();
}
