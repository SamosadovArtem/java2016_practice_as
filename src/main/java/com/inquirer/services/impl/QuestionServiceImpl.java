package com.inquirer.services.impl;

import com.inquirer.dao.QuestionRepository;
import com.inquirer.models.Question;
import com.inquirer.services.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOGER = Logger.getLogger(QuestionServiceImpl.class);

    @Resource
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestions() throws SQLException {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionByNumber(int number) {
        return questionRepository.findOne(number);
    }

    @Override
    public int getQuestionsAmount() {
        return questionRepository.findAll().size();
    }
}
