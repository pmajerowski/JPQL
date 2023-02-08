<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New publisher</title>
</head>
<body>
<form:form method="post" action="/publisher/save" modelAttribute="publisher">
    <p>
        <form:label path="name">Name</form:label>
        <form:input path="name"/>
        <form:errors path="name" cssClass="error"/>
    </p>
    <p>
        <form:label path="nip">Name</form:label>
        <form:input path="nip"/>
        <form:errors path="nip" cssClass="error"/>
    </p>
    <p>
        <form:label path="regon">Name</form:label>
        <form:input path="regon"/>
        <form:errors path="regon" cssClass="error"/>
    </p>
    <p>
        <input type="submit" placeholder="add">
    </p>
    <p>
        <a href="/publisher/list">CANCEL</a>
    </p>
</form:form>
</body>
</html>