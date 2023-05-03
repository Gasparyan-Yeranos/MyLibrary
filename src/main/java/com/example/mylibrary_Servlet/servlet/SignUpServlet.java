package com.example.mylibrary_Servlet.servlet;

import com.example.mylibrary_Servlet.manager.UserManager;
import com.example.mylibrary_Servlet.model.User;
import com.example.mylibrary_Servlet.model.UserType;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")

public class SignUpServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        User user = userManager.getByEmail(email);

        if(user == null){
            userManager.save(User.builder()
                            .name(req.getParameter("name"))
                            .surname(req.getParameter("surname"))
                            .email(req.getParameter("email"))
                            .password(req.getParameter("password"))
                            .userType(UserType.valueOf(req.getParameter("userType")))
                            .build());
            System.out.println("User Signed Up");
        }
        resp.sendRedirect("/");
    }
}
