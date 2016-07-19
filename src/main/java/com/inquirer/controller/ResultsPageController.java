package com.inquirer.controller;

import com.inquirer.exeptions.UserWithoutMarkExeption;
import com.inquirer.models.Result;
import com.inquirer.models.User;
import com.inquirer.services.AnswerService;
import com.inquirer.services.ResultService;
import com.inquirer.services.impl.ResultServiceImpl;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("results")
public class ResultsPageController {

    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        ResultService resultService = new ResultServiceImpl();

        HttpSession session = request.getSession();
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
            request.setAttribute("result", result);
        } catch (UserWithoutMarkExeption ex) {
            String message = "Вы не прошли ни одного теста";
            String s = new String(message.getBytes(), "UTF-8");
            request.setAttribute("result", s);
        }

        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.resultsPage", req);
    }
}
