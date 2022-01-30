package com.example.assignment2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


@WebServlet(name = "LibraryData", value = "/LibraryData")
public class LibraryData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Book> bookList = loadBooks();
        List<Author> authorList = loadAuthors();
        String format = request.getParameter("type");


        if(format.equals("books")) {
            out.println("<a href='index.jsp' style='color:green'>Take me Home! </a>");
            out.print("<div><strong>Books</strong></div>");
            for(int i = 0; i<=3;i++){
                out.println("<br/>");
            }
            for (Book book : bookList) {
                out.println("<hr/><h1 style='color:green'>" + book.getTitle() + "</h1>");
                out.print("<div><strong>Authors</strong></div>");
                for (Author author : book.getAuthorList()) {
                    out.println("<h2 style='color:blue'><strong>Author:</strong> <i style='color:red'>" + author.getFirstName() + " " + author.getLastName() +
                            "</i> <strong style='color:black'>|</strong> <strong style='color:blue'>Author ID#: </Strong><i style='color:red'>" +
                            author.getAuthorID() + "</i></h2>");
                }
            }
        }else if(format.equals("authors")){
            out.println("<a href='index.jsp' style='color:green'>Take me Home! </a>");
            for(int i = 0; i<=3;i++){
                out.println("<br/>");
            }
            for (Author author : authorList) {
                out.println("<hr/><h1 style='color:green'>" + author.getFirstName() + " " + author.getLastName() + "</h1>");
                for (Book book : author.getBookList()) {
                    out.println("<h2 style='color:blue'><strong>Book title:</strong> <i style='color:red'>" + book.getTitle() +
                            "</i>  <strong style='color:black'|</strong>  <strong style='color:blue'>ISBN #:</strong> <i style='color:red'>" +
                            book.getIsbn() + "</i></h2>");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String format = request.getParameter("type");

        if(format.equals("book")){
            String isbn = request.getParameter("isbn");
            String title = request.getParameter("title");
            int editionNumber = Integer.parseInt(request.getParameter("editionNumber"));
            String copyright = request.getParameter("copyright");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            String newBook = "INSERT INTO titles " +
                            "(isbn, title, editionNumber, copyright)" +
                            "VALUES (?, ?, ?, ?)";

            String bookAuthor = "INSERT INTO authors " +
                                "(firstName, lastName) " +
                                "VALUES (?, ?)";

            String getAuthorID = "SELECT authorID FROM authors " +
                                "WHERE firstName = ? and lastName = ?";

            String authorISBN = "INSERT INTO authorisbn " +
                                "(authorID, isbn) " +
                                "VALUES (?, ?)";

           try(Connection conn = DBConnection.initDatabase();
                PreparedStatement ps = conn.prepareStatement(newBook);
                PreparedStatement stmt = conn.prepareStatement(bookAuthor);
                PreparedStatement prepstate = conn.prepareStatement(getAuthorID);
                PreparedStatement state = conn.prepareStatement(authorISBN)){
                    ps.setString(1,isbn);
                    ps.setString(2,title);
                    ps.setInt(3,editionNumber);
                    ps.setString(4,copyright);
                    ps.executeUpdate();
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.executeUpdate();
                    prepstate.setString(1, firstName);
                    prepstate.setString(2, lastName);
                    ResultSet rs = prepstate.executeQuery();
                    rs.last();
                    int authorID = rs.getInt("authorID");
                    state.setInt(1, authorID);
                    state.setString(2, isbn);
                    state.executeUpdate();
                    RequestDispatcher rd=getServletContext().getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException | ServletException e) {
                e.printStackTrace();
            }
        }else if(format.equals("author")){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String newAuthor = "INSERT INTO authors " +
                                "(firstName, lastName) " +
                                "VALUES (?, ?)";

            try(Connection conn = DBConnection.initDatabase();
                PreparedStatement stmt = conn.prepareStatement(newAuthor)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.execute();
                RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException | ServletException e) {
                e.printStackTrace();
            }
        }
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

    public List<Author> loadAuthors(){
        String authQuery = "SELECT * FROM authors;";
        List<Author> authList =  new ArrayList<>();
        try(Connection conn = DBConnection.initDatabase();
            Statement stmt = conn.createStatement();
            ResultSet authSet = stmt.executeQuery(authQuery)) {
            while (authSet.next()) {

                Author author = new Author(authSet.getInt("authorID"),
                                            authSet.getString("firstName"),
                                            authSet.getString("lastName"));

                String getAuthors = "SELECT * FROM titles t " +
                        "INNER JOIN authorisbn a On " +
                        "t.isbn = a.isbn INNER JOIN authors x on " +
                        "a.authorID = x.authorID " +
                        "WHERE x.authorID = ?;";
                PreparedStatement ps = conn.prepareStatement(getAuthors);
                ps.setInt(1, author.getAuthorID());
                ResultSet rsB = ps.executeQuery();
                while (rsB.next()) {
                    Book book = new Book(rsB.getString("isbn"), rsB.getString("title"),
                            rsB.getInt("editionNumber"), rsB.getString("copyright"));
                    author.getBookList().add(book);
                }
                authList.add(author);
            }
            return authList;
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }




}
