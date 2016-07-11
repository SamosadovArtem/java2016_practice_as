package com.inquirer.services;

import com.inquirer.dao.AnswerDao;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;

    @Override
    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        return answerDao.getAnswersForQuestion(question);
    }

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }
}
