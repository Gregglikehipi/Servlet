package com.devcolibri.servlet;

import Materials.UserInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            String path = req.getContextPath() + "/files";
            resp.sendRedirect(path);
        }
        req.getRequestDispatcher("loginInTo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserInfo userInfo = UserInfo.usersData.get(username);
        if (userInfo != null)
            if (username.isEmpty() || password.isEmpty() || !password.equals(userInfo.getPassword())) {
                req.getRequestDispatcher("loginInTo.jsp").include(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("user", userInfo);
                String path = req.getContextPath() + "/files";
                resp.sendRedirect(path);
            }
        else
            req.getRequestDispatcher("loginInTo.jsp").include(req, resp);
    }


}
