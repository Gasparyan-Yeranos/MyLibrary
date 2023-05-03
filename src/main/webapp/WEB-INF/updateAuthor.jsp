<%@ page import="com.example.mylibrary_Servlet.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
</head>
<%Author author = (Author) request.getAttribute("oldAuthor");%>
<body>
<a href="/authors">back</a>
<h2>Update Author</h2>
<form method="post", action="/updateAuthor">
    <input type="hidden" name="id" value="<%=author.getId()%>">
    name:<br>
    <input type="text" name="name" value="<%=author.getName()%>"><br>
    surname:<br>
    <input type="text" name="surname" value="<%=author.getSurname()%>"><br>
    email:<br>
    <input type="text" name="email" value="<%=author.getEmail()%>"><br>
    age:<br>
    <input type="number" name="age" value="<%=author.getAge()%>"><br>
    <input type="submit" value="save">
</form>
</body>
</html>
