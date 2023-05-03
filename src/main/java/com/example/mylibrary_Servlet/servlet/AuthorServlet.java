package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.AuthorManager;
import com.example.mylibrary_Servlet.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = authorManager.getAll();
        req.setAttribute("authors", all);
        req.getRequestDispatcher("WEB-INF/authors.jsp").forward(req, resp);
    }
}