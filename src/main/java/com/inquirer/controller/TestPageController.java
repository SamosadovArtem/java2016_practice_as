package com.inquirer.controller;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.services.impl.AnswerServiceImpl;
import com.inquirer.services.impl.QuestionServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Question currentQuestion = questionService.getQuestionByNumber(Integer.parseInt(request.getParameter("question")));
        String questionTitle = currentQuestion.getTitle();

        List<Answer> answers = new ArrayList<>();
        try {
            answers = answerService.getAnswersForQuestion(currentQuestion);
        } catch (SQLException e) {
            LOGER.error(e);
        }

        int currentUserAnswerId = answerService.getUserAnswerIdByQuestionNumber(Integer.parseInt(request.getParameter("question")));

        request.setAttribute("userAnswersAmount", answerService.getUserAnswersAmount());
        request.setAttribute("currentUserAnswerId", currentUserAnswerId);
        request.setAttribute("questionsAmount", questionService.getQuestionsAmount());
        request.setAttribute("questionNubmer", request.getParameter("question"));
        request.setAttribute("questionParameterNumber", request.getParameter("question"));
        request.setAttribute("nextQuestionNumber", Integer.parseInt(request.getParameter("question").toString())+1);
        request.setAttribute("previousQuestionNumber", Integer.parseInt(request.getParameter("question").toString())-1);
        request.setAttribute("questionTitle", questionTitle);
        request.setAttribute("answers", answers);

        return "inquirer.testPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

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

