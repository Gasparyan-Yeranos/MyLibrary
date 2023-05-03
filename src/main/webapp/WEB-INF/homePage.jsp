<%@ page import="com.example.mylibrary_Servlet.model.User" %>
<%@ page import="com.example.mylibrary_Servlet.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
</head>
<%User user = (User) session.getAttribute("user");%>
<body>
<a href="/logout">Log out</a>
<h2>Welcome <%=user.getName() + " " + user.getSurname()%></h2>
<a href="/authors">Authors</a> |
<a href="/books">Books</a>
</body>
</html>
