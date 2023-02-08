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