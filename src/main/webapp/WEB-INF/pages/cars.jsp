<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 04.02.18
  Time: 12:53
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js" type="text/javascript"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" type="image/png" sizes="16x16">
</head>
<body>
<!-- NAVIGATION -->
<ma:nav/>
<h2><fmt:message key="nav.cars"/></h2>
<div class="w3-container">
    <div class="w3-section">
        <table class="w3-table-all">
            <tr class="w3-red">
                <th><fmt:message key="col.id"/></th>
                <th><fmt:message key="driver"/></th>
                <th><fmt:message key="col.status"/></th>
                <th><fmt:message key="label.car.number"/></th>
                <th><fmt:message key="label.car.mark"/></th>
                <th><fmt:message key="label.car.model"/></th>
                <th><fmt:message key="label.type.bodywork"/></th>
                <th><fmt:message key="label.car.capacity"/></th>
                <th><fmt:message key="label.car.volume"/></th>
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
                        <td><input pattern="\d*\.?\d*" id="cap${car.id}" class="w3-input w3-border" name="capacity" value="${car.maxWeight}"></td>
                        <td><input pattern="\d*\.?\d*" id="vol${car.id}"class="w3-input w3-border" name="volume" value="${car.maxVolume}"></td>
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

<c:if test="${requestScope.errorEditCar != null}">
    <c:if test="${requestScope.errorEditCar eq ''}">
        <div class="w3-modal" style="display: block">
            <div class="w3-modal-content w3-animate-zoom">
                <div class="w3-panel w3-green w3-display-container">
                <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                      class="w3-button w3-green w3-large w3-display-topright">&times;</span>
                    <h3><fmt:message key="message.good"/></h3>
                    <p>Car edited</p>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${requestScope.errorEditCar ne ''}">
        <div class="w3-modal" style="display: block">
            <div class="w3-modal-content w3-animate-zoom">
                <div class="w3-panel w3-redd w3-display-container">
                <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                      class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                    <c:forEach var="error" items="${errorEditCar}">
                        <h3>${error.key}</h3>
                        <p>${error.value}</p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
</c:if>

<%@include file="/WEB-INF/views/portfolio.jspf" %>
<%@include file="/WEB-INF/views/footer.jspf" %>
<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
<script src="${pageContext.request.contextPath}/scripts/cars.js"></script>
<script src="${pageContext.request.contextPath}/scripts/portfolio.js"></script>
</body>
</html>
