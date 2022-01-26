package com.example.assignment2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "LibraryData", value = "/LibraryData")
public class LibraryData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn;
        Statement stmt;

        try {
            conn = DBConnection.initDatabase();
            assert conn != null;
            stmt = conn.createStatement();
            String testQuery = "SELECT * FROM titles";
            ResultSet rs = stmt.executeQuery(testQuery);
            while(rs.next()){

                String title = rs.getString(2);
                // Hello
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>" + title + "</h1>");
                out.println("</body></html>");
            }

            conn.close();
            stmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
