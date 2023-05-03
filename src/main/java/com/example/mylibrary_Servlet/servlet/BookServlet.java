package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.BookManager;
import com.example.mylibrary_Servlet.model.Book;
import com.example.mylibrary_Servlet.model.User;
import com.example.mylibrary_Servlet.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Book> bookList = new ArrayList<>();
        User user = ((User) session.getAttribute("user"));
        if(user.getUserType() == UserType.ADMIN){
            bookList = bookManager.getAll();
        }else if(user.getUserType() == UserType.USER){
            bookList = bookManager.getByUser(user);
        }
        req.setAttribute("books", bookList);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req, resp);
    }
}
