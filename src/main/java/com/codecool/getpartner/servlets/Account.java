package com.codecool.getpartner.servlets;

import com.codecool.getpartner.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/myaccount"})
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        engine.process("myaccount.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        Map<String, String> userInput = new HashMap();
        if(request.getParameter("save-button") != null){
            userInput.put("UserName", request.getParameter("userName"));
            userInput.put("Age", request.getParameter("age"));
            userInput.put("Room", request.getParameter("myRoom"));
            userInput.put("Picture", request.getParameter("picture"));
            userInput.put("Language", request.getParameter("program-language"));
            userInput.put("Bio", request.getParameter("Bio"));
            // Itt kell elkuldeni a mappet a userInputHandlernek
            System.out.println(userInput);
        }

        response.sendRedirect("/myaccount");
    }
}
