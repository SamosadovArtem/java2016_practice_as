package com.inquirer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.inquirer.models.Question;
import org.springframework.jdbc.core.RowMapper;

public class QuestionMapper implements RowMapper<Question> {
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setTitle(rs.getString("title"));
        return question;
    }
}