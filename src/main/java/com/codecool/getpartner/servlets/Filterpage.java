package com.codecool.getpartner.servlets;

import com.codecool.getpartner.config.TemplateEngineUtil;
import com.codecool.getpartner.inputhandler.FilterHandler;
import com.codecool.getpartner.sql.ConnectingDB;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "filter", urlPatterns = "/filter")
public class Filterpage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("id") == null){
            response.sendRedirect("/");
        }

        FilterHandler filterHandler = new FilterHandler();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        List allUserData = null;
        try {
            allUserData = filterHandler.getAlluserDataFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("user", allUserData);
        engine.process("loggedinpage.html", context, response.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        FilterHandler filterHandler = new FilterHandler();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        List<Map> usersearchResult = new ArrayList<>();

        List<String> filterList = new ArrayList<>();
        if(request.getParameter("filter-button") != null){
            if (request.getParameter("gender") != null){
                filterList.add(" gender = '" + request.getParameter("gender") +"'");
            }if (request.getParameter("minimumage").equalsIgnoreCase("null") && request.getParameter("maximumage").equalsIgnoreCase("null")){
                filterList.add(" age BETWEEN '" + request.getParameter(" minimumage ") +"'" + " AND " + request.getParameter(" maximumage ") + "'");
            }if (request.getParameter("myRoom") != null){
                filterList.add(" room = '" + request.getParameter("myRoom") + "'");
            }if (request.getParameter("program-language") != null){
                filterList.add(" favoritelanguage = '" + request.getParameter("program-language") +"'");
            }
        }

        String query = filterHandler.userFilterHandler(filterList);
        try {
            ResultSet result = ConnectingDB.executeQuery(query);
            usersearchResult = filterHandler.castResultsetToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        context.setVariable("user", usersearchResult);
        engine.process("loggedinpage.html", context, response.getWriter());

    }
}


