<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06.07.2016
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
      String site = "http://localhost:8080/inquirer/login" ;
      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", site);
  %>
  </body>
</html>
