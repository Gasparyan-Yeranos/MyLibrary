package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.UserManager;
import com.example.mylibrary_Servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getByEmailAndPassword(email, password);
        HttpSession session = req.getSession();
        if(user != null) {
            session.setAttribute("user", user);
            resp.sendRedirect("/homePage");
        }else {
            session.setAttribute("msg", "Email or Password is incorrect");
            resp.sendRedirect("/");
        }
    }
}
