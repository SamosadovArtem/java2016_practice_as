package com.inquirer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.inquirer.models.Answer;
import org.springframework.jdbc.core.RowMapper;

public class AnswerMapper implements RowMapper<Answer> {
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Answer answer = new Answer();
        answer.setId(rs.getInt("id"));
        answer.setQuestion(rs.getInt("question"));
        answer.setTitle(rs.getString("title"));
        answer.setRight(rs.getBoolean("isRight"));
        return answer;
    }
}