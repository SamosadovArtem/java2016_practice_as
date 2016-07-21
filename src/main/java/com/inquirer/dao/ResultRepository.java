package com.inquirer.dao;

import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Integer> {

    @Query(value = "SELECT * FROM RESULT WHERE USER = ?1 ORDER BY ID DESC", nativeQuery = true)

    List<Result> getLastUserResult(int id) throws UserWithoutMarkExeption;
}
