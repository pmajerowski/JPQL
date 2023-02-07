<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Student</title>
</head>
<body>
    <div style="padding-left: 40px; padding-top: 30px">
    <form:form  method="post" modelAttribute="student">
        <label for="firstName" style="padding: 10px 10px 7px;"><strong>first name</strong></label><br>
        <form:input type="text" path="firstName" cssStyle="width: 200px" />
        <br>

        <label for="lastName" style="padding: 10px 10px 7px;" ><strong>last name</strong></label><br>
        <form:input type="text" path="lastName" cssStyle="width: 200px" />
        <br><br>

        <span style="padding: 10px 10px 7px;" ><strong>gender</strong> </span><br>
        <form:radiobutton path="gender" value="M"/> male <br>
        <form:radiobutton path="gender" value="F"/> female
        <br><br>

        <label for="notes" style="padding: 10px 10px 7px;" ><strong>notes</strong></label><br>
        <form:textarea path="notes" cssStyle="width: 200px; height: 90px" />
        <br><br>

        <label for="country" ><strong>country</strong></label>
        <form:select path="country" items="${countries}" />
        <br><br>

        <form:checkbox id="mailingList" path="mailingList" />
        <label for="mailingList"><strong>mailing list</strong></label>
        <br><br>

        <label for="programmingSkills" style="padding: 10px 10px 7px;" ><strong>programming skills</strong></label><br>
        <form:select path="programmingSkills" items="${skills}" multiple="true"
                     cssStyle="width: 200px; height: 120px; margin-top: 10px"/>
        <br><br>

        <label for="hobbies" style="padding: 10px 10px 7px;"><strong>hobbies</strong></label><br>
        <form:checkboxes items="${hobbies}" path="hobbies" delimiter="<br>"/>
        <br><br>

        <button type="submit" style="background-color: wheat; width: 80px; height: 30px">submit</button>
    </form:form>
    </div>
</body>
</html>