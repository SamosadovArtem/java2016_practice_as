package com.inquirer.services.impl;

import com.inquirer.dao.QuestionDao;
import com.inquirer.dao.impl.QuestionDaoImpl;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.services.QuestionService;

import java.sql.SQLException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

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
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public int getQuestionsAmount() {
        List<Question> questions = null;
        try {
            questions = getQuestions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions.size();
    }
}
