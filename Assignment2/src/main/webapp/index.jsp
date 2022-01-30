<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en-US">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width">
    <title>Library Data</title>
</head>
<body class="backImage">
    <div class="container">
        <img src="welcome.jpg" alt="Welcome to the Library" class="welcome">
        <br/><br/><br/>
        <div class="buttonContainer">
            <a class="btn btn-primary" href="LibraryData?type=books">View Books</a>
            <hr/>
            <a class="btn btn-danger" href="LibraryData?type=authors">View Authors</a>
            <hr/>
            <a class="btn btn-warning" href="addAuthor.jsp">Add Author</a>
            <hr/>
            <a class="btn btn-success" href="addBook.jsp">Add Book</a>
        </div>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>