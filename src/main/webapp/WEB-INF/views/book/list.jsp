<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Book list</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>Id</th>
    <th>Title</th>
    <th>Rating</th>
    <th>Publisher</th>
  </tr>
  </thead>
  <c:forEach items="${books}" var="book">
    <tr>
      <td>${book.id}</td>
      <td><c:out value="${book.title}"/></td>
      <td><c:out value="${book.rating}"/></td>
      <td><c:out value="${book.publisher.name}"/></td>
      <td><a href="/book/edit/${book.id}">edit</a></td>
      <td><a href="/delete/confirm/${book.id}">delete</a></td>
    </tr>
  </c:forEach>
</table>
  <p>
    <a href="/book/new">add new book</a>
  </p>
</body>
</html>