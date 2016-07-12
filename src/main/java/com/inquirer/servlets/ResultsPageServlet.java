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

public class ResultsPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int result = 100; //
        request.setAttribute("result",result);
        TilesContainer container = TilesAccess.getContainer(new ServletApplicationContext(request.getSession().getServletContext()));
        Request req = new ServletRequest(container.getApplicationContext(), request, response);
        container.render("inquirer.resultsPage", req);
    }
}
