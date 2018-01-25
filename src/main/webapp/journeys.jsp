<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma"%>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 22.01.18
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JOURNEYS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="styles/w31.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<ma:nav/>
<h2>JOURNEYS</h2>
<div class="w3-responsive">
    <table class="w3-table-all">
        <tr class="w3-red">
            <th>Id</th>
            <th>Date</th>
            <th>Status</th>
            <th>Price</th>
            <th>Weight</th>
            <th>Material</th>
            <th>Volume</th>
            <th>Manager</th>
            <th>Car</th>
        </tr>
        <c:forEach items="${requestScope.journeys}" var="journey">
            <tr>
                <td>${journey.id}</td>
                <td>${journey.date}</td>
                <td><ma:status category="${journey.status.status}"/></td>
                <td>${journey.price}</td>
                <td>${journey.weight}</td>
                <td>${journey.material.material}</td>
                <td>${journey.volume}</td>
                <td>${journey.idManager}</td>
                <td>${journey.idCar}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/views/footer.jspf" %>
</body>
</html>
