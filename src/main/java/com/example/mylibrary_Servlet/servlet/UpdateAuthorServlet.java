package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.AuthorManager;
import com.example.mylibrary_Servlet.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateAuthor")
public class UpdateAuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Author author = authorManager.getById(id);
        if(author == null){
            resp.sendRedirect("/authors");
        }else{
            req.setAttribute("oldAuthor", author);
            req.getRequestDispatcher("WEB-INF/updateAuthor.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author newAuthor = Author.builder()
                .id(Integer.valueOf(req.getParameter("id")))
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .age(Integer.valueOf(req.getParameter("age")))
                .build();
        authorManager.update(newAuthor);
        resp.sendRedirect("/authors");
    }
}
