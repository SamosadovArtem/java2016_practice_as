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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test")
public class TestPageController {

    @Autowired private AnswerServiceImpl answerService;
    @Autowired private QuestionServiceImpl questionService;

    @RequestMapping(method = RequestMethod.GET)
    protected String getQuestion(@RequestParam("question") String question, Model model){

        Question currentQuestion = questionService.getQuestionByNumber(Integer.parseInt(question));
        String questionTitle = currentQuestion.getTitle();

        List<Answer> answers = new ArrayList<>();
        answers = answerService.getAnswersForQuestion(currentQuestion);

        int currentUserAnswerId = answerService.getUserAnswerIdByQuestionNumber(Integer.parseInt(question));

        model.addAttribute("userAnswersAmount", answerService.getUserAnswersAmount());
        model.addAttribute("currentUserAnswerId", currentUserAnswerId);
        model.addAttribute("questionsAmount", questionService.getQuestionsAmount());
        model.addAttribute("questionNubmer", question);
        model.addAttribute("questionParameterNumber", question);
        model.addAttribute("nextQuestionNumber", Integer.parseInt(question.toString())+1);
        model.addAttribute("previousQuestionNumber", Integer.parseInt(question.toString())-1);
        model.addAttribute("questionTitle", questionTitle);
        model.addAttribute("answers", answers);

        return "inquirer.testPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String acceptQuestion(@RequestParam("userAnswerInput") String userAnswerInput,
                                 @RequestParam("questionParameterNumber") String questionParameterNumber,
                                 HttpSession session){

        if (userAnswerInput!=null) {
            int id = Integer.parseInt(userAnswerInput);


            answerService.setUserAnswer(id);

            if (questionService.getQuestionsAmount() == answerService.getUserAnswersAmount()) {
                session.setAttribute("userResult", answerService.getTestResult());
                session.setAttribute("userAnswers", answerService);
            }
        }
        return "redirect:test?question=" + questionParameterNumber;
    }
}

