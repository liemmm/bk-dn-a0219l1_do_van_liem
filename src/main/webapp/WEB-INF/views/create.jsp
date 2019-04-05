<%--
  Created by IntelliJ IDEA.
  User: liemmm
  Date: 03/04/2019
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Create a new student </h1>
<p>
    <c:if test="${requestScope['message'] != null }">
        ${requestScope['message']}
    </c:if>
</p>
<form:form method="post" modelAttribute="student">
    <table>
        <tr>
            <td><form:label path="id">ID:</form:label></td>
            <td><form:input path="id" placeholder=""></form:input></td>
        </tr>
        <tr>
            <td><form:label path="name">name:</form:label></td>
            <td><form:input path="name"></form:input></td>
        </tr>
        <tr>
    Gender:
        Male <form:radiobutton path="gender" value="Male"/>
        Famale <form:radiobutton path="gender" value="Female"/>
        </tr>
        <tr>
            <td><form:label path="address">address:</form:label></td>
            <td><form:input path="address"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="classs">class:</form:label></td>
            <td><form:input path="classs"></form:input></td>
        </tr>
        <tr>
            <td>Add :</td>
            <td><input type="submit" value="Create a new student"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
