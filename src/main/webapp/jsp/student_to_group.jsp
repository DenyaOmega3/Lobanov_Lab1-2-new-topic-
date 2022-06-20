<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 18.06.22
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="/base?command=studentWithoutGroup" />
<head>
    <title>Title</title>

    <p>Choose student</p>

    <form action="/base">
        <input type="hidden" name="command" value="addStudents">
        <label for="${student.student.lastName}">Surname:</label> <br/>
        <c:forEach var="student" items="${studentsWithoutGroup}">
            <input type="checkbox" name="student" id="student" value="${student.student.id}"> <label for = "student">${student.student.lastName}</label> <br/>
        </c:forEach>

        <c:forEach var="group" items="${groups}">
            <input type="radio" name="group" id="group" value="${group.id}"> <label for = "group">${group.codename}${group.number}</label> <br/>
        </c:forEach>
        <input type="submit" value="send to group">
    </form>
</head>
<body>

</body>
</html>
