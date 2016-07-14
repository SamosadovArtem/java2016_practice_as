package com.inquirer.servlets;


import com.inquirer.models.User;
import com.inquirer.services.impl.UserServiceImpl;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Егор on 11.07.2016.
 */
public class LoginPageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("status","User name");
        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.loginPage", req);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        user.setName(request.getParameter("login"));

        UserServiceImpl userLoginService = new UserServiceImpl();

        if(userLoginService.isUserExist(user)) {
            response.sendRedirect("test?question=1");
        } else{
            request.setAttribute("status","User not found");
            TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
            Request req = new ServletRequest(container.getApplicationContext(), request, response);
            container.render("inquirer.loginPage", req);
        }
    }
}
