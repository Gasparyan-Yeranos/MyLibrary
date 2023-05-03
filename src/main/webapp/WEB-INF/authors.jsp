<%@ page import="com.example.mylibrary_Servlet.model.User" %>
<%@ page import="com.example.mylibrary_Servlet.model.UserType" %>
<%@ page import="com.example.mylibrary_Servlet.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
</head>
<%User user= (User) session.getAttribute("user");
  List<Author> all = (List<Author>) request.getAttribute("authors");%>
<body>
<a href="/homePage">Back</a> |
<a href="/logout">Log out</a>
<h2>Authors</h2>
<%if(user.getUserType() == UserType.ADMIN)%><a href="/addAuthor">add author</a>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Email</th>
        <%if(user.getUserType() == UserType.ADMIN)%><th>Action</th>
    </tr>
    <%if(all != null && !all.isEmpty()){%>
    <%for (Author author: all) {%>
        <tr>
            <td><%=author.getId()%></td>
            <td><%=author.getName()%></td>
            <td><%=author.getSurname()%></td>
            <td><%=author.getAge()%></td>
            <td><%=author.getEmail()%></td>
            <%if(user.getUserType() == UserType.ADMIN){%>
            <td><a href="/updateAuthor?id=<%=author.getId()%>">update</a> /
            <a href="/removeAuthor?id=<%=author.getId()%>">remove</a></td>
            <%}%>
        </tr>
    <%}%>
    <%}else{%>
    <tr>
        <td colspan="6">No authors yet.</td>
    </tr>
    <%}%>
</table>
</body>
</html>
