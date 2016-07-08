package com.inquirer.servlets;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 06.07.2016.
 */
public class HelloServlet  extends HttpServlet {

//    private String responseTemplate =
//            "<html>\n" +
//                    "<body>\n" +
//                    "<h2>Hello from Simple Servlet</h2>\n" +
//                    "</body>\n" +
//                    "</html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.homepage", req);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        this.process(request, response);
//    }

//    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(200);
//        response.getWriter().write(responseTemplate);
//    }
}

