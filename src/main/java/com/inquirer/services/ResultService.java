package com.inquirer.services;

import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;

public interface ResultService {

    public Result getLastUserResult(User user) throws UserWithoutMarkExeption;

    public void addUserResult(Result result);

}
