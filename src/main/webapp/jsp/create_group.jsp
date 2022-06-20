<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 18.06.22
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/base">
    <input type="hidden" name="command" value="createGroup">
    <input type = "radio" name = "group_code" id = "KA" value="KA"> <label for = "KA">KA</label> <br/>
    <input type = "radio" name = "group_code" id = "DA" value="DA"> <label for = "DA">DA</label> <br/>
    <input type= "text" name = "group_number" required>
    <br/>
    <input type="submit" value="Create group">
</form>
</body>
</html>
