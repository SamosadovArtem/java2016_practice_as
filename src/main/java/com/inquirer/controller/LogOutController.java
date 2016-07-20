package com.inquirer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("logout")
public class LogOutController {
    @RequestMapping(method = RequestMethod.GET)
    protected String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
}
