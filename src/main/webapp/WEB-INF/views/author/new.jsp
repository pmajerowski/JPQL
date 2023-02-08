<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New author</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body>
<form:form method="post" action="/author/save" modelAttribute="author">
    <p>
        <form:label path="firstName">First name</form:label>
        <form:input path="firstName"/>
        <form:errors path="firstName" cssClass="error"/>
    </p>
    <p>
        <form:label path="lastName">Last name</form:label>
        <form:input path="lastName"/>
        <form:errors path="lastName" cssClass="error"/>
    </p>
    <p>
        <form:label path="pesel">PESEL</form:label>
        <form:input path="pesel"/>
        <form:errors path="pesel" cssClass="error"/>
    </p>
    <p>
        <form:label path="email">e-mail</form:label>
        <form:input path="email"/>
        <form:errors path="email" cssClass="error"/>
    </p>
    <p>
        <input type="submit" placeholder="add">
    </p>
    <p>
        <a href="/author/list">CANCEL</a>
    </p>
</form:form>
</body>
</html>