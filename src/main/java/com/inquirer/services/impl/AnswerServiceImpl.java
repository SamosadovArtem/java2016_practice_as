package com.inquirer.services.impl;

import com.inquirer.dao.AnswerDao;
import com.inquirer.dao.impl.AnswerDaoImpl;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.services.AnswerService;

import java.sql.SQLException;
import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;

    public AnswerServiceImpl(){
        answerDao = new AnswerDaoImpl();
    }

    @Override
    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        return answerDao.getAnswersForQuestion(question);
    }

}
