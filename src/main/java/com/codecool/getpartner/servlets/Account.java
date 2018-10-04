package com.codecool.getpartner.servlets;


import com.codecool.getpartner.config.TemplateEngineUtil;
import com.codecool.getpartner.inputhandler.FilterHandler;
import com.codecool.getpartner.inputhandler.UserAccountHandler;
import com.codecool.getpartner.sql.ConnectingDB;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

@WebServlet(name = "myaccount", urlPatterns = {"/myaccount"},
    initParams = {@WebInitParam(name="path", value = "/var/www/upload/")})
@MultipartConfig
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("id") == null){
            response.sendRedirect("/");
        }
        FilterHandler filterHandler = new FilterHandler();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        List<Map> listOfUserData = new ArrayList<>();
        try {
            listOfUserData.add(filterHandler.getUserbyId((String)session.getAttribute("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("user", listOfUserData);
        engine.process("myaccount.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserAccountHandler userAccount = new UserAccountHandler();
        Map<String, String> userInput = new HashMap();
        if(request.getParameter("save-button") != null){
            userInput.put("username", request.getParameter("userName"));
            userInput.put("age", request.getParameter("age"));
            userInput.put("room", request.getParameter("myRoom"));

            Part filePart = request.getPart("myfile");
            HttpSession session = request.getSession();
            String currentUserId = (String)session.getAttribute("id");

            if(userAccount.isUserUploadFileAImage(filePart)){
                userInput.put("picture", filePart.getSubmittedFileName());
                userAccount.fileUploader(filePart);
            }
            userInput.put("favoritelanguage", request.getParameter("program-language"));
            userInput.put("bio", request.getParameter("Bio"));
            userAccount.userAccountChanges(userInput, currentUserId);
        }
        response.sendRedirect("/myaccount");
    }
}
