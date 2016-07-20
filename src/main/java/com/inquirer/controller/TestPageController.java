package com.inquirer.controller;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.services.impl.AnswerServiceImpl;
import com.inquirer.services.impl.QuestionServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test")
public class TestPageController {

    private static final Logger LOGER = Logger.getLogger(TestPageController.class);

    @Autowired private AnswerServiceImpl answerService;
    @Autowired private QuestionServiceImpl questionService;

    @RequestMapping(method = RequestMethod.GET)
    protected String getQuestion(HttpServletRequest request, Model model) throws IOException {

        Question currentQuestion = questionService.getQuestionByNumber(Integer.parseInt(request.getParameter("question")));
        String questionTitle = currentQuestion.getTitle();

        List<Answer> answers = new ArrayList<>();
        try {
            answers = answerService.getAnswersForQuestion(currentQuestion);
        } catch (SQLException e) {
            LOGER.error(e);
        }

        int currentUserAnswerId = answerService.getUserAnswerIdByQuestionNumber(Integer.parseInt(request.getParameter("question")));

        model.addAttribute("userAnswersAmount", answerService.getUserAnswersAmount());
        model.addAttribute("currentUserAnswerId", currentUserAnswerId);
        model.addAttribute("questionsAmount", questionService.getQuestionsAmount());
        model.addAttribute("questionNubmer", request.getParameter("question"));
        model.addAttribute("questionParameterNumber", request.getParameter("question"));
        model.addAttribute("nextQuestionNumber", Integer.parseInt(request.getParameter("question").toString())+1);
        model.addAttribute("previousQuestionNumber", Integer.parseInt(request.getParameter("question").toString())-1);
        model.addAttribute("questionTitle", questionTitle);
        model.addAttribute("answers", answers);

        return "inquirer.testPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String acceptQuestion(HttpServletRequest request, HttpSession session) throws IOException, ServletException {

        if (request.getParameter("userAnswerInput")!=null) {
            int id = Integer.parseInt(request.getParameter("userAnswerInput"));


            answerService.setUserAnswer(id);

            if (questionService.getQuestionsAmount() == answerService.getUserAnswersAmount()) {
                session.setAttribute("userResult", answerService.getTestResult());
                session.setAttribute("userAnswers", answerService);
                //answerService.clearUserAnswers();
            }
        }
        return "redirect:test?question=" + request.getParameter("questionParameterNumber");
    }
}

