<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 18.06.22
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="/base?command=studentWithoutGroup" />
<head>
</head>

<body onload="foo()">
<form method="post" enctype="multipart/form-data" action="/base">
    <input type="hidden" name="command" value="addHomework">
    <input type="hidden" name="user_id" value="${param.user_id}">
    <input type="text" id = "name" name="name" required>
    <label for="name">Name:</label>
    <br/>

    <input type="text" id = "description" name="description" required>
    <label for="description">Description:</label>
    <br/>
    <br/>
    <label for="file_upload">Load pdf file here:</label> <br/>
    <input type="file" accept="application/pdf" id="file_upload" name="file_upload">
    <br/>

    <label for="datefield">Choose deadline date</label> <br/>
    <input type="date" name = "date" id = "datefield" min = "1970-01-01"  value="${param.time}"> <br/>

    <label>Choose for which group</label> <br/>
    <c:forEach var="group" items="${groups}">
        <input type="radio" name="group_id" id="group" value="${group.id}"> <label for = "group">${group.codename}${group.number}</label> <br/>
    </c:forEach>
    <input type="submit" name = "s1" value="load">
</form>

<script src="../javascript/dynamic.js"></script>
</body>

</html>
