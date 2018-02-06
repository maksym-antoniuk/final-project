<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 04.02.18
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>Summary Task 4</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/${sessionScope.theme}.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js" type="text/javascript"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" type="image/png" sizes="16x16">
</head>
<body>
<!-- NAVIGATION -->
<ma:nav/>
<h2><fmt:message key="nav.potential.cars"/></h2>
<div class="w3-container">
    <div class="w3-section">
        <table class="w3-table-all">
            <tr class="w3-red">
                <th><fmt:message key="col.id"/></th>
                <th><fmt:message key="label.car.number"/></th>
                <th><fmt:message key="label.car.mark"/></th>
                <th><fmt:message key="label.car.model"/></th>
                <th><fmt:message key="label.type.bodywork"/></th>
                <th><fmt:message key="label.car.capacity"/></th>
                <th><fmt:message key="label.car.volume"/></th>
                <th><fmt:message key="driver"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${requestScope.potentialCars}" var="car">
            <tr>
                <td>${car.id}</td>
                <td>${car.number}</td>
                <td>${car.mark}</td>
                <td>${car.model}</td>
                <td>${car.bodywork.bodywork}</td>
                <td>${car.maxWeight}</td>
                <td>${car.maxVolume}</td>
                <td class="portfolioButton" portfolioid="${car.idUser}"><i class="fa fa-vcard-o"></i></td>
                <td><i class="fa fa-plus w3-text-green" onclick="addCar('${car.id}')"></i></td>
                <td><i class="fa fa-minus w3-text-red" onclick="removePotentialCar('${car.id}');"></i></td>
                </c:forEach>
        </table>
    </div>
</div>

<c:if test="${requestScope.errorAcceptPotentialCar != null}">
    <div id="driverCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
            <c:if test="${requestScope.errorAcceptPotentialCar != ''}">
                <div class="w3-panel w3-redd w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                    <h3>Danger!</h3>
                    <p>${requestScope.errorAcceptPotentialCar}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.errorAcceptPotentialCar == ''}">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Car has accepted</p>
                </div>
            </c:if>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.errorRemovePotentialCar != null}">
    <div id="driverCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
            <c:if test="${requestScope.errorRemovePotentialCar != ''}">
                <div class="w3-panel w3-redd w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                    <h3>Danger!</h3>
                    <p>${requestScope.errorRemovePotentialCar}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.errorRemovePotentialCar == ''}">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Car has deleted</p>
                </div>
            </c:if>
        </div>
    </div>
</c:if>
<%@include file="/WEB-INF/views/portfolio.jspf" %>
<%@include file="/WEB-INF/views/footer.jspf" %>
<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
<script src="${pageContext.request.contextPath}/scripts/potential-car.js"></script>
</body>
</html>
