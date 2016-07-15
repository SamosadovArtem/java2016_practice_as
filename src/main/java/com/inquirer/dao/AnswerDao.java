package com.inquirer.dao;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;

import java.sql.SQLException;
import java.util.List;

public interface AnswerDao {

    List<Answer> getAnswersForQuestion(Question question) throws SQLException;
    Answer getAnswerById(int id) throws SQLException;
}
