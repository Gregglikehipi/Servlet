package org.example;

import Materials.UserInfo;

import java.sql.*;

public class SQLConnection {

    static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    static final String USER = "root";
    static final String PASS = "root123";
    static final String QUERY1 = "SELECT login, pass, mail FROM user";

    public static void setConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY1);) {
            // Extract data from result set
            while (rs.next()) {
                UserInfo.usersData.put(rs.getString("login"), new UserInfo(rs.getNString("login"),
                        rs.getNString("pass"), rs.getNString("mail")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void setUser(UserInfo user) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String QUERY2 = String.format("INSERT INTO user VALUES ('%s', '%s', '%s')", user.getLogin(), user.getPassword(), user.getMailAddress());
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ) {
            stmt.executeUpdate(QUERY2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
