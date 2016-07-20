package com.inquirer.dao.impl;

import com.inquirer.dao.AnswerRepository;
import com.inquirer.mapper.AnswerMapper;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private static final String SELECT_ANSWER_FOR_QUESTION_QUERY = "SELECT * FROM answer WHERE question = ?";
    private static final String SELECT_ANSWER_BY_ID_QUERY = "SELECT * FROM answer WHERE answer.id = ?";

    private static final Logger LOGER = Logger.getLogger(AnswerRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Answer> getAnswersForQuestion(Question question){
        List<Answer> answers  = jdbcTemplate.query(SELECT_ANSWER_FOR_QUESTION_QUERY,new Object[]{question.getId()},
                new AnswerMapper());
        return answers;
    }

    @Override
    public Answer getAnswerById(int id){
        Answer answer = jdbcTemplate.queryForObject(SELECT_ANSWER_BY_ID_QUERY,new Object[]{id},new AnswerMapper());
        return answer;
    }
}
