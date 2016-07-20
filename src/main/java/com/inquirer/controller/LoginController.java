package com.inquirer.controller;


import com.inquirer.models.User;
import com.inquirer.services.UserService;
import com.inquirer.services.impl.UserServiceImpl;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired private UserService userLoginService;

    @RequestMapping(method = RequestMethod.GET)
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "inquirer.loginPage";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        User user = new User();

        user.setName(request.getParameter("login"));

        //UserServiceImpl userLoginService = new UserServiceImpl();

        if(userLoginService.isUserExist(user)) {
            HttpSession session = request.getSession();
            user = userLoginService.getUserByName(user.getName());
            session.setAttribute("user",user);
            return "redirect:/test?question=1";
        } else {
            request.setAttribute("status","User not found");
            return "inquirer.loginPage";
        }
    }
}
