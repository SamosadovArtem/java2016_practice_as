package com.inquirer.servlets;

import com.inquirer.dao.ResultDao;
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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResultsPageServlet extends HttpServlet {

    @Override
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
        } catch (UserWithoutMarkExeption userWithoutMarkExeption) {
            request.setAttribute("result", "Вы не прошли ни одного теста");
        }

        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.resultsPage", req);
    }
}
