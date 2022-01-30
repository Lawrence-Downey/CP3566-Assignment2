<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width">
    <title>Add Book</title>
</head>
<body class="openBook">
<span class="addBook-centered">
    <h1 class="text-secondary text-success"><strong>Add Book</strong></h1>
    <span>
    <form method="post" action="LibraryData">
        <input type="hidden" name="type" value="book">
            <div class="form-group">
                <label for="isbn" class="text-danger"><strong>ISBN #:</strong></label>
                <input type="text" class="form-control" id="isbn" name="isbn" placeholder="8862145632000"
                       style="width:200%">
            </div>
            <br />
            <div class="form-group">
                <label for="title" class="text-danger"><strong>Book Title:</strong></label>
                <input type="text" class="form-control" id="title" name="title" placeholder="The Lion Attacked"
                       style="width:200%">
            </div>
            <br />
            <div class="form-group">
                <label for="editionNumber" class="text-danger"><strong>Edition Number:</strong></label>
                <input type="text" class="form-control" id="editionNumber" name="editionNumber" placeholder="3"
                       style="width:200%">
            </div>
            <br />
            <div class="form-group">
                <label for="copyright" class="text-danger"><strong>Copyright:</strong></label>
                <input type="text" class="form-control" id="copyright" name="copyright" placeholder="2012"
                       style="width:200%">
            </div>
            <br/><br/>
    </form>
    </span>
    <span>
        <h1 class="text-secondary text-success"><strong>Add Author</strong></h1>
        <form method="post" action="LibraryData">
            <div class="form-group">
                <label for="firstName" class="text-danger"><strong>First Name:</strong></label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Claude"
                       style="width:200%">
            </div>
            <br />
            <div class="form-group">
                <label for="lastName" class="text-danger"><strong>Last Name:</strong></label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Yarmoff"
                       style="width:200%">
            </div>
            <br />
        <button type="submit" class="btn btn-success" style="width: auto">Add Book</button>
        </form>
    </span>
</span>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
