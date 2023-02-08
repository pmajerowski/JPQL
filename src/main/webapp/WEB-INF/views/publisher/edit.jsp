<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: przemek
  Date: 07/02/2023
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit publisher</title>
</head>
<body>
<form:form method="post" action="/publisher/edit/save/${publisherToEdit.id}" modelAttribute="publisherToEdit">
    <h1>${publisherToEdit.id}</h1>
    <p>
        <form:label path="name">Name</form:label>
        <form:input path="name"/>
    </p>

    <p>

        <form:hidden path="id"/>
        <input id="id" name="id" type="hidden" value="${publisherToEdit.id}"/>
        <input type="submit" />
    </p>
</form:form>
<p>
    <a href="/publisher/list">CANCEL</a>
</p>

</body>
</html>