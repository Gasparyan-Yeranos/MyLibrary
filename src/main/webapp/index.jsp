<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Book Store</title>
</head>
<body>
<h1>Welcome!</h1>
<%
  if (session.getAttribute("user") != null) {
    response.sendRedirect("/homePage");
  }
  String msg = (String) session.getAttribute("msg");
%>
<% if (msg != null) {%>
<span style="color: red"><%=msg%></span><br>
<%
    session.removeAttribute("msg");
  }%>
<h3>log in please</h3>
<form action="/login" method="post">
  Username:<input type="text" name="email"><br/>
  Password: <input type="text" name="password"><br/> <span>               </span>
  <input type="submit" value="log in">
</form>
if you don't have an account, <a href="signUp.jsp">click here</a>
</body>
</html>