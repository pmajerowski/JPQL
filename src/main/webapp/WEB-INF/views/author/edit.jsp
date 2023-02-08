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
    <title>Edit author</title>
</head>
<body>
<form:form method="post" action="/author/edit/save/${authorToEdit.id}" modelAttribute="authorToEdit">
    <h1>${authorToEdit.id}</h1>
    <p>
        <form:label path="firstName">First name</form:label>
        <form:input path="firstName"/>
    </p>
    <p>
        <form:label path="lastName">Last name</form:label>
        <form:input path="lastName"/>
    </p>

    <p>

        <form:hidden path="id"/>
        <input id="id" name="id" type="hidden" value="${bookToEdit.id}"/>
        <input type="submit" />
    </p>
</form:form>
<p>
    <a href="/author/list">CANCEL</a>
</p>

</body>
</html>