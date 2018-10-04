package com.codecool.getpartner.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "filter", urlPatterns = "/filter")
public class Filterpage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<String> filter = new ArrayList<>();
        if (req.getParameter("gender") != null){
            filter.add(" gender = " + req.getParameter("gender"));
        }if (req.getParameter("minimumage") != null && req.getParameter("maximumage") != null){
            filter.add(" age BETWEEN " + req.getParameter(" minimumage ") + " AND " + req.getParameter(" maximumage "));
        }if (req.getParameter("myRoom") != null){
            filter.add(" room = " + req.getParameter("myRoom"));
        }if (req.getParameter("program-language") != null){
            filter.add(" favoritelanguage = " + req.getParameter("program-language"));
        }



    }
}


