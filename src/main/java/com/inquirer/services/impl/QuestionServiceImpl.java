package com.inquirer.services.impl;

import com.inquirer.dao.QuestionDao;
import com.inquirer.dao.impl.QuestionDaoImpl;
import com.inquirer.models.Question;
import com.inquirer.services.QuestionService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOGER = Logger.getLogger(QuestionServiceImpl.class);

    private QuestionDao questionDao;

    public QuestionServiceImpl(){
        questionDao = new QuestionDaoImpl();
    }

    @Override
    public List<Question> getQuestions() throws SQLException {
        return questionDao.getQuestions();
    }

    @Override
    public Question getQuestionByNumber(int number) {
        Question question = null;
        try {
            question = questionDao.getQuestionByNumber(number);
        } catch (SQLException e) {
            LOGER.error(e);
        }
        return question;
    }

    @Override
    public int getQuestionsAmount() {
        List<Question> questions = null;
        try {
            questions = getQuestions();
        } catch (SQLException e) {
            LOGER.error(e);
        }
        return questions.size();
    }
}
