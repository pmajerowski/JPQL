<%--
  Created by IntelliJ IDEA.
  User: przemek
  Date: 07/02/2023
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form:form method="post" modelAttribute="person">
    <label for="login" style="padding: 10px 10px 7px;" >login</label><br>
    <form:input type="text" path="login" />
    <br>
    <br>

    <label for="email" style="padding: 10px 10px 7px;" >email</label>
    <br>
    <form:input type="email" path="email" />
    <br>
    <br>

    <label for="password" style="padding: 10px 10px 7px;" >password</label>
    <br>
    <form:input type="password" path="password" />
    <br>
    <br>

    <button type="submit">submit</button>

  </form:form>

</body>
</html>
