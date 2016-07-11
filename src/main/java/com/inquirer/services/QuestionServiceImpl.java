package com.inquirer.services;

import com.inquirer.dao.QuestionDao;
import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    @Override
    public List<Question> getQuestions() throws SQLException {
        return questionDao.getQuestions();
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }
}
