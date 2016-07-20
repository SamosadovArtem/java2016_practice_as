package com.inquirer.dao;

import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;

public interface ResultRepository {

    Result getLastUserResult(User user) throws UserWithoutMarkExeption;

    void addUserResult(Result result);

}
