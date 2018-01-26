<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 09.01.18
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>ADMIN POTENTIAL USER</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../../styles/w31.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
    <link rel="icon" href="../../img/icon.png" type="image/png" sizes="16x16">
</head>
<body>
<%@include file="/views/nav.jspf" %>
<table class="w3-table">
    <c:forEach items="${requestScope.potentialUsers}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.lastname}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.role.role}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/journey">GGGGG</a>
<%@include file="/views/footer.jspf" %>
</body>
</html>