<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Publishers</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>Id</th>
    <th>Name</th>
  </tr>
  </thead>
  <c:forEach items="${publishers}" var="publisher">
    <tr>
      <td>${publisher.id}</td>
      <td><c:out value="${publisher.name}"/></td>

      <td><a href="/publisher/edit/${publisher.id}">edit</a></td>
      <td><a href="/publisher/confirm/${publisher.id}">delete</a></td>
    </tr>
  </c:forEach>
</table>
<p>
  <a href="/publisher/new">add new publisher</a>
</p>
</body>
</html>