<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 26.01.18
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/${sessionScope.theme}.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<!-- NAVIGATION -->
<ma:nav/>
<h2><fmt:message key="nav.users"/></h2>
<input class="w3-input w3-border w3-padding" type="text" placeholder="Search for names.." id="myInputName" onkeyup="nameFilter()">
<input class="w3-input w3-border w3-padding" type="text" placeholder="Search for surnames.." id="myInputSurname" onkeyup="surnameFilter()">
<div class="w3-responsive">
    <table class="w3-table-all" id="tableUser">
        <tr class="w3-red">
            <th><fmt:message key="col.status"/></th>
            <th><fmt:message key="col.id"/></th>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.surname"/></th>
            <th><fmt:message key="label.email"/></th>
            <th><fmt:message key="col.salary"/></th>
            <th><fmt:message key="col.role"/></th>
            <th><fmt:message key="label.phone"/></th>
            <th><fmt:message key="col.dateReg"/></th>
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
<script src="${pageContext.request.contextPath}/scripts/user.js"></script>
<script src="${pageContext.request.contextPath}/scripts/register_potential_user.js"></script>
</body>
</html>
