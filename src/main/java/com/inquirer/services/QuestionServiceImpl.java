package com.inquirer.services;

import com.inquirer.dao.QuestionDao;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.models.User;

import java.sql.SQLException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void addUser(User user) throws SQLException {
        questionDao.addUser(user);
    }

    @Override
    public List<Answer> getAnswersForQuestion(Question question) throws SQLException {
        return questionDao.getAnswersForQuestion(question);
    }

    @Override
    public List<Question> getQuestions() throws SQLException {
        return questionDao.getQuestions();
    }

    @Override
    public List<User> getUsers() throws SQLException {
        return questionDao.getUsers();
    }
}
