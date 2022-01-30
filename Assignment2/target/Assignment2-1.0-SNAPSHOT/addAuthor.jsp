<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp"%>

<!DOCTYPE html>
<html lang="en-US">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width">
    <title>Add Author</title>
</head>
<body class="openBook">
<div class="addAuthor-centered">
    <h1 class="text-secondary text-success"><strong>Add Author</strong></h1>
    <form method="post" action="LibraryData">
        <input type="hidden" name="type" value="author">
        <div class="form-group">
            <label for="firstName" class="text-success"><strong>First Name:</strong></label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Marcus"
                   style="width:200%">
        </div>
        <br />
        <div class="form-group">
            <label for="lastName" class="text-success"><strong>Last Name:</strong></label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Absent"
                   style="width:200%">
        </div>
        <br />
        <button type="submit" class="btn btn-success" style="margin-left: 85px">Add Author</button>
    </form>
</div>
<a href="index.jsp" style="color:green">Take me Home!</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
