package com.inquirer.controller;

import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import com.inquirer.services.AnswerService;
import com.inquirer.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("results")
public class ResultsPageController {

    @Autowired private ResultService resultService;
    @RequestMapping(method = RequestMethod.GET)
    protected String watchResult(HttpSession session, Model model) throws UnsupportedEncodingException {
        User user = (User) session.getAttribute("user");

        if (session.getAttribute("userResult")!=null){
            Result result = new Result();
            result.setUser(user.getId());
            result.setMark((int)session.getAttribute("userResult"));
            resultService.addUserResult(result);
            AnswerService service = (AnswerService)session.getAttribute("userAnswers");
            service.clearUserAnswers();
        }

        try {

            int result = resultService.getLastUserResult(user).getMark();
            model.addAttribute("result", result);
        } catch (UserWithoutMarkExeption ex) {
            model.addAttribute("result", new String("Вы не прошли ни одного теста".getBytes(),"UTF-8"));
        }

        return "inquirer.resultsPage";
    }
}
