<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>First name</th>
        <th>Last name</th>
    </tr>
    </thead>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td>${author.id}</td>
            <td><c:out value="${author.firstName}"/></td>
            <td><c:out value="${author.lastName}"/></td>

            <td><a href="/author/edit/${author.id}">edit</a></td>
            <td><a href="/author/confirm/${author.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>
<p>
    <a href="/author/new">add new author</a>
</p>
</body>
</html>