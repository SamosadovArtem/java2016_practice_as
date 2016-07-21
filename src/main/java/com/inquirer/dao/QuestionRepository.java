package com.inquirer.dao;

import com.inquirer.models.Question;
import com.inquirer.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
