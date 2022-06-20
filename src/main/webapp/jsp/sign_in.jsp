<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 18.06.22
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <form action="/base">
        <input type="hidden" name="command" value="signIn">
        <input type="text" id = "email" name="email" required>
        <label for="email">Email:</label>
        <br/>
        <input type="password" id = "password" name="password" required>
        <label for="password">Password:</label>
        <br/>
        <input type="submit" value="Sign in">
    </form>
</head>
<body>

</body>
</html>
