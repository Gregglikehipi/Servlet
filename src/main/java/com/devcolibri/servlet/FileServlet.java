package com.devcolibri.servlet;

import Materials.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.StreamSupport;

@WebServlet("/files")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo == null) {
            String path1 = req.getContextPath() + "/login";
            resp.sendRedirect(path1);
            return;
        }

        String path = req.getParameter("path");

        if (path == null) {
            path = "C:\\WebUsers\\" + userInfo.getLogin();
            resp.sendRedirect(String.format("%s%s?path=%s", req.getContextPath(), req.getServletPath(), URLEncoder.encode(path, StandardCharsets.UTF_8)));
            return;
        }
        Path testFilePath = Paths.get(path);
        testFilePath = testFilePath.getFileName();
        String same = testFilePath.toString();
        if (same.equals("WebUsers")) {
            path = "C:\\WebUsers\\" + userInfo.getLogin();
            resp.sendRedirect(String.format("%s%s?path=%s", req.getContextPath(), req.getServletPath(), URLEncoder.encode(path, StandardCharsets.UTF_8)));
            return;
        }


        String date = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(LocalDateTime.now());
        req.setAttribute("date", date);

        req.setAttribute("path", path);

        String directorateAtTheTop = new File(path).getParent();
        if (directorateAtTheTop != null) {
            req.setAttribute("directoryVisibilityOnTop", "block");
            req.setAttribute("directorateAtTheTop", directorateAtTheTop);
        } else {
            req.setAttribute("directoryVisibilityOnTop", "none");
        }

        outputContentsDir(req, path);

        req.getRequestDispatcher("fileServlet.jsp").forward(req, resp);
    }

    private void outputContentsDir(HttpServletRequest req, String path) {
        File f = new File(path);
        File[] allFiles = f.listFiles();

        if (allFiles != null) {
            List<File> directories = new ArrayList<>();
            List<File> files = new ArrayList<>();

            for (File file : allFiles) {
                if (file.getPath() != null) {
                    if (file.isDirectory())
                        directories.add(file);
                    else
                        files.add(file);
                }
            }

            req.setAttribute("directories", directories);
            req.setAttribute("files", files);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("POST method isn't available");
    }
}
