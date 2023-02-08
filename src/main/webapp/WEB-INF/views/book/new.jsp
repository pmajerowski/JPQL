<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>New book</title>
</head>
<body>
<form:form method="post" action="/book/save" modelAttribute="book">
  <p>
    <form:label path="title">Title</form:label>
    <form:input path="title"/>
  </p>

  <p>
    Authors:<br>
    <form:select path="authors"  itemLabel="name" itemValue="id" items="${authors}"/>
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
    <input type="submit" placeholder="add">
  </p>
  <p>
    <a href="/book/list">CANCEL</a>
  </p>
</form:form>
</body>
</html>