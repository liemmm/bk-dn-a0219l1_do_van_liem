<%--
  Created by IntelliJ IDEA.
  User: lllll
  Date: 3/11/2019
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    List products
</h1>
<hr/>
<h2><a href="/products"> back to list </a></h2>
<form method="post">
    <legend>Customer information</legend>
    <table>
        <tr>
            <td> name :</td>
            <td>
                <input type="text" name="name" id="name">
            </td>
        </tr>
        <tr>
            <td> price :</td>
            <td>
                <input type="text" name="price" id="price">
            </td>
        </tr>
    </table>
        <input type="submit" value="create">

</form>
</body>
</html>
