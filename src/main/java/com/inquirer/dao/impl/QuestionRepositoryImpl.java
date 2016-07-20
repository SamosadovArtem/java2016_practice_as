package com.inquirer.dao.impl;

import com.inquirer.dao.QuestionRepository;
import com.inquirer.mapper.QuestionMapper;
import com.inquirer.models.Question;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private static final String SELECT_ALL_QUESTION_QUERY = "SELECT * FROM question";
    private static final String SELECT_QUESTION_BY_NUMBER_QUERY = "SELECT * FROM question WHERE id = ?";

    private static final Logger LOGER = Logger.getLogger(QuestionRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Question> getQuestions(){
        List<Question> questions = jdbcTemplate.query(SELECT_ALL_QUESTION_QUERY,new QuestionMapper());
        return questions;
    }

    @Override
    public Question getQuestionByNumber(int number){
        Question question = jdbcTemplate.queryForObject(SELECT_QUESTION_BY_NUMBER_QUERY, new Object[]{number},
                new QuestionMapper());
        return question;
    }

}
