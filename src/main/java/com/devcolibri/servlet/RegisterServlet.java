package com.devcolibri.servlet;

import Materials.UserInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        if (username.isEmpty() || password.isEmpty() || address.isEmpty()) {
            req.getRequestDispatcher("register.jsp").include(req, resp);
        }
        else {
            String pathFile = "C:\\WebUsers\\" + username;
            File theDir = new File(pathFile);
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            UserInfo.usersData.put(username, new UserInfo(username, password, address));
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }
    }
}