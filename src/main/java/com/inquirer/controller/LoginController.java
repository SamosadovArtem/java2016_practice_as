package com.inquirer.controller;


import com.inquirer.models.User;
import com.inquirer.services.impl.UserServiceImpl;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.loginPage", req);
    }
    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        user.setName(request.getParameter("login"));

        UserServiceImpl userLoginService = new UserServiceImpl();

        if(userLoginService.isUserExist(user)) {
            HttpSession session = request.getSession();
            user = userLoginService.getUserByName(user.getName());
            session.setAttribute("user",user);
            response.sendRedirect("test?question=1");
        } else {
            request.setAttribute("status","User not found");
            TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
            Request req = new ServletRequest(container.getApplicationContext(), request, response);
            container.render("inquirer.loginPage", req);
        }
    }
}
