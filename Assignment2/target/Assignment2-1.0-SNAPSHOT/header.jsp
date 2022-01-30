
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-primary bg-opacity-50 justify-content-center fixed-top">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" style="color: forestgreen" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="LibraryData?type=books" style="color: oldlace">View Books</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="LibraryData?type=authors" style="color: forestgreen">View Authors</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="addBook.jsp" style="color: oldlace">Add Book</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="addAuthor.jsp" style="color: forestgreen">Add Author</a>
        </li>
    </ul>
</nav>
</body>
</html>
