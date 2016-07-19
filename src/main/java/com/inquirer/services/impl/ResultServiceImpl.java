package com.inquirer.services.impl;

import com.inquirer.dao.ResultDao;
import com.inquirer.dao.impl.ResultDaoImpl;
import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import com.inquirer.services.ResultService;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    private ResultDao resultDao;

    public ResultServiceImpl(){
        resultDao = new ResultDaoImpl();
    }

    @Override
    public Result getLastUserResult(User user) throws UserWithoutMarkExeption{
        return resultDao.getLastUserResult(user);
    }

    @Override
    public void addUserResult(Result result) {
        resultDao.addUserResult(result);
    }
}
