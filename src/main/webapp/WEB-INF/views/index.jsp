<%--
  Created by IntelliJ IDEA.
  User: lllll
  Date: 3/28/2019
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="get" action="/convert">
    <h1>List student</h1>
    <form>
        <table>
            <tr>
                <td>ID</td>
                <td>name</td>
                <td>age</td>
                <td>gender</td>
                <td>address</td>
                <td>class</td>
            </tr>
            <c:forEach items='${requestScope["students"]}' var="student">
                <tr>
                    <td><a>${student.getId()}</a></td>
                    <td><a>${student.getName()}</a></td>
                    <td><a>${student.getAge()}</a></td>
                    <td><a>${student.getGender()}</a></td>
                    <td><a>${student.getAddress()}</a></td>
                    <td><a>${student.getClasss()}</a></td>
                    <td><a href="/delete?id=${student.getId()}">Delete</a></td>
                    <td><a href="/edit?id=${student.getId()}">Edit</a></td>

                </tr>
            </c:forEach>
        </table>
    </form>
    <button><a href="/create">update student </a></button>
</form:form>
</body>
</html>
