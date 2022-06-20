<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 17.06.22
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="/base">
        <input type="hidden" name="command" value="loadPDF">
        <input type="hidden" name="user_id" value="${param.user_id}">
        <label for="file_upload">Load here:</label>
        <input type="file" accept="application/pdf" id="file_upload" name="file_upload">
        <input type="submit" name = "s1" value="load">
    </form>

    <c:forEach var="pdf" items="${listPDF}">
        <a href="/hello?pdf_id=${pdf.id}">${pdf.id}</a>
    </c:forEach>

</body>
</html>
