package com.codecool.getpartner.servlets;
import com.codecool.getpartner.inputhandler.FilterHandler;
import com.codecool.getpartner.inputhandler.Login;
import com.codecool.getpartner.inputhandler.Registration;

import com.codecool.getpartner.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.FileHandler;

@WebServlet(urlPatterns = {"/"})
public class MainPage extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilterHandler filterHandler = new FilterHandler();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List allUserData = null;
        try {
            allUserData = filterHandler.getAlluserDataFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("user", allUserData);
//        try {
//            while(result.next()){
//                    context.setVariable("userName", result.getString("username"));
//            }
//        }catch (SQLException e) {
//                e.printStackTrace();
//        }

        engine.process("index.html", context, resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        if (req.getParameter("register")!= null) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Registration newRegister = new Registration();
            try {
                newRegister.registing(email, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/");
        }else {
            String loginEmail = req.getParameter("login email");
            String loginPassword = req.getParameter("login password");
            Login login = new Login();
            try {
                if (login.checkEmailAndPassword(loginEmail, loginPassword)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("id", login.getIdByEmail(loginEmail));
                    out.print("Welcome, " + loginEmail);
                } else {
                    req.getRequestDispatcher("index.html").include(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
