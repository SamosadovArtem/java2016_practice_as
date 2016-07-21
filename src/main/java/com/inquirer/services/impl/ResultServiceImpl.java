package com.inquirer.services.impl;

import com.inquirer.dao.ResultRepository;
import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import com.inquirer.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Resource
    private ResultRepository resultRepository;

    @Override
    public Result getLastUserResult(User user) throws UserWithoutMarkExeption{

        List<Result> results = resultRepository.getLastUserResult(user.getId());
        if (results.isEmpty()){
            throw new UserWithoutMarkExeption("User have no marks");
        }
        return results.get(0);
    }

    @Override
    public void addUserResult(Result result) {
        resultRepository.save(result);
    }
}
