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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestPageServlet extends HttpServlet {
    private AnswerServiceImpl answerService = new AnswerServiceImpl();
    private QuestionServiceImpl questionService = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Question currentQuestion = questionService.getQuestionByNumber(Integer.parseInt(request.getParameter("question")));
        String questionTitle = currentQuestion.getTitle();

        List<Answer> answers = new ArrayList<>();
        try {
            answers = answerService.getAnswersForQuestion(currentQuestion);
        } catch (SQLException e) {
            e.printStackTrace();
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

        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.testPage", req);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

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

        response.sendRedirect("test?question=" + request.getParameter("questionParameterNumber"));
    }
}

