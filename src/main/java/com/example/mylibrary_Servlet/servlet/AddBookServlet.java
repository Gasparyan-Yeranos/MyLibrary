package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.AuthorManager;
import com.example.mylibrary_Servlet.manager.BookManager;
import com.example.mylibrary_Servlet.manager.UserManager;
import com.example.mylibrary_Servlet.model.Author;
import com.example.mylibrary_Servlet.model.Book;
import com.example.mylibrary_Servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();
    private UserManager userManager = new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authorList", authorManager.getAll());
        req.getRequestDispatcher("WEB-INF/addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = Book.builder()
                .title(req.getParameter("title"))
                .author(authorManager.getById(Integer.valueOf(req.getParameter("authorId"))))
                .description(req.getParameter("description"))
                .price(req.getParameter("price"))
                .user(userManager.getById(Integer.valueOf(req.getParameter("userId"))))
                .build();
        bookManager.save(book);
        resp.sendRedirect("/books");
    }
}
