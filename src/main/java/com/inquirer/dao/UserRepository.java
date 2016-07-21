package com.inquirer.dao;

import com.inquirer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * FROM USER WHERE NAME = ?1", nativeQuery = true)

    User getUserByName(String name);
}
