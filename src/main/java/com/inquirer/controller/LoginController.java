package com.inquirer.controller;


import com.inquirer.models.User;
import com.inquirer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired private UserService userLoginService;

    @RequestMapping(method = RequestMethod.GET)
    protected String login(){
        return "inquirer.loginPage";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String acceptUser(HttpServletRequest request, HttpSession session, Model model) {
        User user = new User();

        user.setName(request.getParameter("login"));

        if(userLoginService.isUserExist(user)) {
            user = userLoginService.getUserByName(user.getName());
            session.setAttribute("user",user);
            return "redirect:/test?question=1";
        } else {
            model.addAttribute("status","User not found");
            return "inquirer.loginPage";
        }
    }
}
