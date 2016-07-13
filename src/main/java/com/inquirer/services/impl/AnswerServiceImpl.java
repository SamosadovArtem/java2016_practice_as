package com.inquirer.services.impl;

import com.inquirer.dao.AnswerDao;
import com.inquirer.dao.impl.AnswerDaoImpl;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.services.AnswerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;
    private List<Answer> userAnswers;

    public AnswerServiceImpl(){
        answerDao = new AnswerDaoImpl();
        userAnswers = new ArrayList<>();
    }

    @Override
    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        return answerDao.getAnswersForQuestion(question);
    }

    @Override
    public Answer getAnswerById(int id) throws SQLException {
        return answerDao.getAnswerById(id);
    }

    public void setUserAnswer(int id) {
        try {
            userAnswers.add(getAnswerById(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTestResult() {
        int correctAnswers = 0;
        for (Answer currentAnswer:userAnswers) {
            if (currentAnswer.isRight())
                correctAnswers++;
        }
        return correctAnswers;
    }
}
