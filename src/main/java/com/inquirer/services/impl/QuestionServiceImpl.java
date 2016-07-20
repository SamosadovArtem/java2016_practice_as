package com.inquirer.services.impl;

import com.inquirer.dao.QuestionRepository;
import com.inquirer.models.Question;
import com.inquirer.services.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOGER = Logger.getLogger(QuestionServiceImpl.class);

    @Autowired private QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestions() throws SQLException {
        return questionRepository.getQuestions();
    }

    @Override
    public Question getQuestionByNumber(int number) {
        Question question = null;
        try {
            question = questionRepository.getQuestionByNumber(number);
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
