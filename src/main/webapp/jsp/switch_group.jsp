<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 21.06.22
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="/base?command=studentWithoutGroup" />
<head>
    <title>Title</title>

    <form action="/base">
        <input type="hidden" name="command" value="updateGroup">
        <input type="hidden" name="user_id" value="${param.user_id}">
        <c:forEach var="group" items="${groups}">
            <input type="radio" name="group" id="group" value="${group.id}"> <label for = "group">${group.codename}${group.number}</label> <br/>
        </c:forEach>
        <input type="submit" value="send to group">
    </form>
</head>
<body>

</body>
</html>
