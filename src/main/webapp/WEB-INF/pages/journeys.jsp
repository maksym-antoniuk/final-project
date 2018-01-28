<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="../mytag.tld" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/w31.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
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
            <th>Map</th>
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
                <td><i class="fa fa-map w3-large" onclick="openMap('${journey.from}', '${journey.where}');"></i></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="map" class="w3-modal">
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal">&times;</span>
            <h2>Route</h2>
        </header>
        <div class="w3-container w3-modal-content">
            <iframe id="map_iframe" src="#" width="870" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
    </div>
</div>
<form>
    Enter Your Name: <input type="text" id="userName" />
</form>
<br>
<br>
<script src="${pageContext.request.contextPath}/scripts/journey.js"></script>
<strong>Ajax Response</strong>:
<div id="ajaxGetUserServletResponse"></div>

<%@include file="/WEB-INF/views/footer.jspf" %>
</body>
</html>
