package com.example.assignment2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LibraryData", value = "/LibraryData")
public class LibraryData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Book> bookList = loadBooks();
        for(Book book : bookList) {
            out.println("<hr/><h1 style='color:blue'>" + book.getTitle() +"</h1>");
            for(Author author : book.getAuthorList()) {
                out.println("<h2 style='color:red'>" + author.getFirstName() + " " + author.getLastName() + " | Author ID#: " + author.getAuthorID() + "</h2>");
            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

     public List<Book> loadBooks(){
        String booksQuery = "SELECT * FROM titles;";
        List<Book> bookList =  new ArrayList<>();
        try(Connection conn = DBConnection.initDatabase();
            Statement stmt = conn.createStatement();
            ResultSet bookSet = stmt.executeQuery(booksQuery)) {
            while (bookSet.next()) {

                Book book = new Book(bookSet.getString("isbn"), bookSet.getString("title"),
                        bookSet.getInt("editionNumber"), bookSet.getString("copyright"));

                String getBooks = "SELECT * FROM titles t " +
                        "INNER JOIN authorisbn a On " +
                        "t.isbn = a.isbn INNER JOIN authors x on " +
                        "a.authorID = x.authorID " +
                        "WHERE t.isbn = ?;";
                PreparedStatement ps = conn.prepareStatement(getBooks);
                ps.setString(1, book.getIsbn());
                ResultSet authSet = ps.executeQuery();
                while (authSet.next()) {
                    Author author = new Author(authSet.getInt("authorID"), authSet.getString("firstName"),
                            authSet.getString("lastName"));
                    book.getAuthorList().add(author);
                }
                bookList.add(book);
            }
            return bookList;
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }



}
