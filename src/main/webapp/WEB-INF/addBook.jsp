<%@ page import="com.example.mylibrary_Servlet.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary_Servlet.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
</head>
<%List<Author> authorList = (List<Author>) request.getAttribute("authorList");%>
<%User user = (User) session.getAttribute("user");%>
<body>
<a href="/books">back</a>
<form method="post" action="/addBook">
    title:<br>
    <input type="text" name="title"><br>
    author:<br>
    <select name="authorId">
        <% for (Author author: authorList) { %>
                <option value="<%=author.getId()%>"><%=author.getId()%></option>
        <% } %>
    </select><br>
    description:<br>
    <input type="text" name="description"><br>
    price:<br>
    <input type="text" name="price"><br>
    <input type="hidden" name="userId" value="<%=user.getId()%>">
    <input type="submit" value="add">
</form>
</body>
</html>
