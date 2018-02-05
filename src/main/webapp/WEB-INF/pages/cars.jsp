<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="../mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 04.02.18
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Summary Task 4</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/w31.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" type="image/png" sizes="16x16">
</head>
<body>
<!-- NAVIGATION -->
<ma:nav/>
<h2>Cars</h2>
<div class="w3-container">
    <div class="w3-section">
        <table class="w3-table-all">
            <tr class="w3-red">
                <th>Id</th>
                <th>User</th>
                <th>Status</th>
                <th>Number</th>
                <th>Mark</th>
                <th>Model</th>
                <th>Bodywork</th>
                <th>Capacity</th>
                <th>Max Volume</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${requestScope.allCars}" var="car">
                <form action="${pageContext.request.contextPath}/car" method="post">
                    <tr>
                        <td>${car.id}</td>
                        <td class="portfolioButton" portfolioid="${car.idDriver}"><i class="fa fa-vcard-o w3-xlarge w3-hover-text-red"></i></td>
                        <td>${car.status.status}</td>

                        <input hidden name="edit" value="${car.id}">
                        <td><input id="num${car.id}" class="w3-input w3-border" name="car_number" value="${car.number}"
                                   required></td>
                        <td>${car.mark}</td>
                        <td>${car.model}</td>
                        <td>
                            <select class="w3-select" name="type_bodywork" required id="type${car.id}">
                                <option value="tank" <c:if test="${car.bodywork.bodywork eq 'tank'}">selected</c:if>>Tank</option>
                                <option value="bulk" <c:if test="${car.bodywork.bodywork eq 'bulk'}">selected</c:if>>Bulk</option>
                                <option value="animal" <c:if test="${car.bodywork.bodywork eq 'animal'}">selected</c:if>>Animal</option>
                                <option value="container" <c:if test="${car.bodywork.bodywork eq 'container'}">selected</c:if>>Container</option>
                                <option value="car" <c:if test="${car.bodywork.bodywork eq 'car'}">selected</c:if>>Car</option>
                            </select>
                        </td>
                        <td><input id="cap${car.id}" class="w3-input w3-border" name="capacity" value="${car.maxWeight}"></td>
                        <td><input id="vol${car.id}"class="w3-input w3-border" name="volume" value="${car.maxVolume}"></td>
                        <td>
                            <button class="w3-button" style="background: none"
                                    onclick="editCar('${car.id}',$('#num${car.id}'),$('#type${car.id}'),$('#cap${car.id}'), $('#vol${car.id}'))"><i
                                    class="fa fa-edit w3-text-green w3-xlarge"></i></button>
                        </td>
                        <td><button class="w3-button" style="background: none" onclick="removeCar('${car.id}');"> <i class="fa fa-times w3-text-red w3-xlarge"></i></button></td>
                </form>

            </c:forEach>
        </table>
    </div>
</div>
<%@include file="/WEB-INF/views/portfolio.jspf" %>
<%@include file="/WEB-INF/views/footer.jspf" %>
<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
<script src="${pageContext.request.contextPath}/scripts/cars.js"></script>
<script src="${pageContext.request.contextPath}/scripts/portfolio.js"></script>
</body>
</html>
