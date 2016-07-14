package com.inquirer.servlets;

import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.services.impl.AnswerServiceImpl;
import com.inquirer.services.impl.QuestionServiceImpl;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 06.07.2016.
 */
public class HelloServlet  extends HttpServlet {
    private AnswerServiceImpl answerService = new AnswerServiceImpl();
    private QuestionServiceImpl questionService = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Question currentQuestion = questionService.getQuestionByNumber(Integer.parseInt(request.getParameter("question")));
        String questionTitle = currentQuestion.getTitle();

        List<Answer> answers = null;
        try {
            answers = answerService.getAnswersForQuestion(currentQuestion);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int currentUserAnswerId = answerService.getUserAnswerIdByQuestionNumber(Integer.parseInt(request.getParameter("question")));

        request.setAttribute("currentUserAnswerId", currentUserAnswerId);
        request.setAttribute("questionsAmount", questionService.getQuestionsAmount());
        request.setAttribute("questionNubmer", request.getParameter("question"));
        request.setAttribute("questionParameterNumber", request.getParameter("question"));
        request.setAttribute("nextQuestionNumber", Integer.parseInt(request.getParameter("question").toString())+1);
        request.setAttribute("previousQuestionNumber", Integer.parseInt(request.getParameter("question").toString())-1);
        request.setAttribute("questionTitle", questionTitle);
        request.setAttribute("answers", answers);

        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.testPage", req);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("userAnswerInput"));
        answerService.setUserAnswer(id);

        response.sendRedirect("test?question=" + request.getParameter("questionParameterNumber"));
    }
}

