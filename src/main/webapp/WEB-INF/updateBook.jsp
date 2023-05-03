<%@ page import="com.example.mylibrary_Servlet.model.Book" %>
<%@ page import="com.example.mylibrary_Servlet.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary_Servlet.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
</head>
<%Book book = (Book) request.getAttribute("oldBook");%>
<%User user = (User) session.getAttribute("user");%>
<%List<Author> authorList = (List<Author>) request.getAttribute("authorList");%>
<body>
<a href="/books">back</a>
<h2>Update Book</h2>
<form method="post", action="/updateBook">
    <input type="hidden" name="id" value="<%=book.getId()%>">
    title:<br>
    <input type="text" name="title" value="<%=book.getTitle()%>"><br>
    author:<br>
    <select name="authorId" value="<%=book.getAuthor().getId()%>">
        <% for (Author author: authorList) { %>
        <option value="<%=author.getId()%>"><%=author.getId()%></option>
        <% } %>
    </select><br>
    description:<br>
    <input type="text" name="description" value="<%=book.getDescription()%>"><br>
    price:<br>
    <input type="text" name="price" value="<%=book.getPrice()%>"><br>
    <input type="hidden" name="userId" value="<%=user.getId()%>">
    <input type="submit" value="save">
</form>
</body>
</html>