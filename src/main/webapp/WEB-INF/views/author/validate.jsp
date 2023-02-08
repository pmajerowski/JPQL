<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>Author validate</title>
</head>
<body>
<c:if test="${empty violations}">
    <p>
        Author is valid!
    </p>
</c:if>
<c:if test="${not empty violations}">
    <c:forEach var="violation" items="${violations}" >
        ${violation.propertyPath} : ${violation.message} <br>
    </c:forEach>
</c:if>
    <p>

    </p>

</body>
</html>
