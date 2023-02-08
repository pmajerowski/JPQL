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
    <title>Edit book</title>
</head>
<body>
<form:form method="post" action="/book/edit/save/${bookToEdit.id}" modelAttribute="bookToEdit">
    <h1>${bookToEdit.id}</h1>
    <p>
        <form:label path="title">Title</form:label>
        <form:input path="title"/>
    </p>
    <p>
        <form:label path="authors">Authors</form:label>
        <form:select itemValue="id" itemLabel="name" path="authors" items="${authors}" />
    </p>
    <p>
        <form:label path="rating">Rating</form:label>
        <form:input path="rating"/>
    </p>
    <p>
        <form:label path="description">Description</form:label>
        <form:input path="description"/>
    </p>
    <p>
        <form:label path="publisher">Publisher</form:label>
        <form:select itemValue="id" itemLabel="name" path="publisher" items="${publishers}" />
    </p>
    <p>

        <form:hidden path="id"/>
        <input id="id" name="id" type="hidden" value="${bookToEdit.id}"/>
        <input type="submit" />
    </p>
</form:form>
<p>
    <a href="/book/list">CANCEL</a>
</p>

</body>
</html>
