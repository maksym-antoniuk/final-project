<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="../mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 26.01.18
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/w31.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<!-- NAVIGATION -->
<ma:nav/>

<div class="w3-responsive">
    <table class="w3-table-all">
        <tr class="w3-red">
            <th>Status</th>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Salary</th>
            <th>Role</th>
            <th>Phone</th>
            <th>Date Registration</th>
        </tr>
        <c:forEach items="${requestScope.allUsers}" var="user">
            <tr>
                <td><ma:isOnline onlineList="${applicationScope.onlineUsers}" user="${user}"/></td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>${user.salary}</td>
                <td><ma:role role="${user.role.role}"/></td>
                <td>${user.phone}</td>
                <td>${user.dateReg}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/WEB-INF/views/footer.jspf" %>
<script src="${pageContext.request.contextPath}/scripts/main.js"></script>
<script src="${pageContext.request.contextPath}/scripts/register_potential_user.js"></script>
</body>
</html>
