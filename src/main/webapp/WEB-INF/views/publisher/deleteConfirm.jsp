<%--
  Created by IntelliJ IDEA.
  User: przemek
  Date: 08/02/2023
  Time: 08:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Confirm</title>
</head>
<body>
<div style="margin-block-end: fill; size: auto; border: darkgreen">
  <h3>Are you sure you want to delete this publisher?</h3>
  <h4>This is irreversible.</h4>
  <a href="/publisher/delete/${id}">DELETE</a>
  <a href="/publisher/list">CANCEL</a>

</div>

</body>
</html>