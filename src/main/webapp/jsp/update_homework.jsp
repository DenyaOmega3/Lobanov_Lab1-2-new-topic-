<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 19.06.22
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body onload="foo()">
<form method="post" enctype="multipart/form-data" action="/base">
    <input type="hidden" name="command" value="updateHomework">
    <input type="hidden" name="homework_id" value="${homework_id}">
    <input type="hidden" name="user_id" value="${user_id}">
    <input type="hidden" name="group_id" value="${group_id}">
    <input type="text" id = "name" name="name" value="${name}" required>
    <label for="name">Name:</label>
    <br/>

    <input type="text" id = "description" name="description" value="${description}" required>
    <label for="description">Description:</label>
    <br/>
    <br/>
    <label for="file_upload">Load <u>another</u> pdf file here or leave empty:</label> <br/>
    <input type="file" accept="application/pdf" id="file_upload" name="file_upload">
    <br/>

    <label for="datefield">Choose deadline date</label> <br/>
    <input type="date" name = "date" id = "datefield" min = "1970-01-01"  value="${deadline}"> <br/>

    <label>Choose for which group</label> <br/>
    <c:forEach var="group" items="${groups}">

        <c:choose>
            <c:when test="${group_codename == group.codename and group_number == group.number}">
                <input checked type="radio" name="group_id" id="group" value="${group.id}">
            </c:when>
            <c:otherwise>
                <input type="radio" name="group_id" id="group" value="${group.id}">
            </c:otherwise>
        </c:choose>
        <label for = "group">${group.codename}${group.number}</label> <br/>
    </c:forEach>
    <input type="submit" name = "s1" value="load">
</form>

<script src="../javascript/dynamic.js"></script>
</body>
</html>
