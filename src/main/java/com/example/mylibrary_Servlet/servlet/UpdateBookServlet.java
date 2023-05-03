package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.AuthorManager;
import com.example.mylibrary_Servlet.manager.BookManager;
import com.example.mylibrary_Servlet.manager.UserManager;
import com.example.mylibrary_Servlet.model.Author;
import com.example.mylibrary_Servlet.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();
    private UserManager userManager = new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Book book = bookManager.getById(id);
        if(book != null){
            req.setAttribute("oldBook", book);
            req.setAttribute("authorList", authorManager.getAll());
            req.getRequestDispatcher("WEB-INF/updateBook.jsp").forward(req, resp);
        }else{
            resp.sendRedirect("/books");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = Book.builder()
                .id(Integer.valueOf(req.getParameter("id")))
                .title(req.getParameter("title"))
                .author(authorManager.getById(Integer.valueOf(req.getParameter("authorId"))))
                .description(req.getParameter("description"))
                .price(req.getParameter("price"))
                .user(userManager.getById(Integer.valueOf(req.getParameter("userId"))))
                .build();
        bookManager.update(book);
        resp.sendRedirect("/books");
    }
}
