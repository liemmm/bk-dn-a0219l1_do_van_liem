<%@ page import="model.Product" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lllll
  Date: 3/9/2019
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>homepage</title>
</head>
<body>
<h1>
    List products
</h1>
<hr/>
<h2><a href="/products?action=create"> create a new customer </a></h2>
<table border="2">
    <tr>
        <td>ID</td>
        <td>name</td>
        <td>price</td>
    </tr>

    <%
        ArrayList<Product> items = (ArrayList<Product>) request.getAttribute("products");
    %>
    <%
        for (Product i : items
        ) {
            int id = i.getId();
            String name = i.getName();
            double price = i.getPrice();
    %>
    <tr>
        <td><%=id%>
        </td>
        <td><%=name%>
        </td>
        <td><%=price%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
