package com.inquirer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.inquirer.models.Result;
import org.springframework.jdbc.core.RowMapper;

public class ResultMapper implements RowMapper<Result> {
    public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
        Result result = new Result();
        result.setId(rs.getInt("id"));
        result.setUser(rs.getInt("user"));
        result.setMark(rs.getInt("mark"));
        return result;
    }
}