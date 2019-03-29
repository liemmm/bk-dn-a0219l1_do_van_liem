<%--
  Created by IntelliJ IDEA.
  User: lllll
  Date: 3/29/2019
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/dictionary">
    <h1>English</h1>
    <input type="text" name="englishWord">
    <h1>từ dịch ra là: ${word}</h1>
    <input type="submit"/>
</form>
</body>
</html>
