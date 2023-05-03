<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
</head>
<body>
<a href="/authors">back</a>
<h2>Add Author</h2>
<form action="/addAuthor" method="post">
    Name: <input name="name" type="text"><br>
    Surname: <input name="surname" type="text"><br>
    Age: <input name="age" type="number"><br>
    Email: <input name="email" type="text"><br>
    <input type="submit" value="add">
</form>
</body>
</html>
