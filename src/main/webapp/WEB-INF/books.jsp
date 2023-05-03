<%@ page import="com.example.mylibrary_Servlet.model.User" %>
<%@ page import="com.example.mylibrary_Servlet.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary_Servlet.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
</head>
<%
  List<Book> all = (List<Book>) request.getAttribute("books");
  User user = (User) request.getAttribute("user");
  if(user == null){
    response.sendRedirect("/homePage");
  }%>
<body>
<a href="/homePage">Back</a> |
<a href="/logout">Log out</a>
<h2>Books</h2>
<a href="/addBook">add book</a>
<table border="1">
  <tr>
    <th>Id</th>
    <th>Title</th>
    <th>Author</th>
    <th>Description</th>
    <th>Price</th>
    <%if(user.getUserType() == UserType.ADMIN) %> <th>user</th>
    <th>Action</th>
  </tr>
  <%if(all != null && !all.isEmpty()){%>
  <%for (Book book: all) {%>
  <tr>
    <td><%=book.getId()%></td>
    <td><%=book.getTitle()%></td>
    <td><%=book.getAuthor().getId()%></td>
    <td><%=book.getDescription()%></td>
    <td><%=book.getPrice()%></td>
    <%if(user.getUserType() == UserType.ADMIN) %> <td><%=book.getUser().getId()%></td>
    <td><a href="/updateBook?id=<%=book.getId()%>">update</a> /
      <a href="/removeBook?id=<%=book.getId()%>">remove</a></td>
  </tr>
  <%}%>
  <%}else{%>
  <tr>

    <td colspan="<%=(user.getUserType() == UserType.ADMIN)?7:6%>">No books yet.</td>
  </tr>
  <%}%>
</table>
</body>
</html>
