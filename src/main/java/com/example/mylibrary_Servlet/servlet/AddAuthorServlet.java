package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.AuthorManager;
import com.example.mylibrary_Servlet.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAuthor")
public class AddAuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/addAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if(authorManager.getByEmail(email) == null) {
            Author author = Author.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(req.getParameter("email"))
                    .age(Integer.valueOf(req.getParameter("age")))
                    .build();
            authorManager.save(author);
        }
        resp.sendRedirect("/authors");
    }
}
