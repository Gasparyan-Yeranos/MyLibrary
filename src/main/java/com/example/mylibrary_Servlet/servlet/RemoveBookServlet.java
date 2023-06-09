package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.BookManager;
import com.example.mylibrary_Servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {
    BookManager bookManager = new BookManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        HttpSession session = req.getSession();
        bookManager.removeById(id,(User) session.getAttribute("user"));
        resp.sendRedirect("/books");
    }
}
