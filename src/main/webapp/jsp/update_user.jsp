<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 21.06.22
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <form action="/base">
        <input type="hidden" name="command" value="updateUser">
        <input type="hidden" name="id" value="${id}">
        <label for="first_name">First name:</label><br>
        <input type="text" id="first_name" name="first_name" value="${first_name}" required><br>
        <label for="last_name">Last name:</label><br>
        <input type="text" id="last_name" name="last_name" value="${last_name}" required><br>
        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" value="${email}" required>
        <br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" value="${password}" required><br>
        <input type = "radio" name = "typo" id = "teacher" value="teacher"> <label for = "teacher">Teacher</label> <br/>
        <input checked type = "radio" name = "typo" id = "student" value="student"> <label for = "student">Student</label> <br/>
        <input type="submit"  value="update user">
    </form>
</head>
<body>

</body>
</html>
