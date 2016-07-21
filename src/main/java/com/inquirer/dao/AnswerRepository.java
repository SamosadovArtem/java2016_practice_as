package com.inquirer.dao;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.SQLException;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    @Query(value = "SELECT * FROM answer WHERE question = ?1", nativeQuery = true)
    List<Answer> getAnswersForQuestion(int id) throws SQLException;
}
