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
    <title>Potential users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/w31.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<!-- NAVIGATION -->
<ma:nav/>


<div class="w3-container" id="mytabs">
    <h2>Potential Users</h2>


    <div class="w3-bar w3-black">
        <button class="w3-bar-item w3-button tablink w3-red" onclick="openRole(event,'manager')">Manager</button>
        <button class="w3-bar-item w3-button tablink" onclick="openRole(event,'driver')">Driver</button>
    </div>

    <div id="manager" class="w3-container w3-border role">
        <h2>Manager</h2>
        <div class="w3-container">
            <div class="w3-section w3-responsive">
                <table class="w3-table-all">
                    <tr class="w3-red">
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${requestScope.managers}" var="manager">
                    <tr>
                        <td>${manager.name}</td>
                        <td>${manager.lastname}</td>
                        <td>${manager.email}</td>
                        <td>${manager.phone}</td>
                        <td>
                            <div class="w3-dropdown-click">
                                <i class="fa fa-plus w3-text-green" onclick="salaryManager('${manager.id}')"></i>
                                <div id="${manager.id}_man"
                                     class="w3-dropdown-content w3-bar-block w3-card-12 w3-animate-zoom">
                                    <input class="w3-row w3-input w3-border w3-half" placeholder="Salary"
                                           id="${manager.id}_salary" value="0">
                                    <button class="w3-row w3-white w3-btn-block w3-padding w3-quarter"
                                            onclick="addManager('${pageContext.request.contextPath}/potential-user', '${manager.id}');">
                                        <i class="fa fa-toggle-right w3-large w3-text-green"></i></button>
                                </div>
                            </div>
                        </td>
                        <td><i class="fa fa-minus w3-text-red"
                               onclick="cancelUser('${pageContext.request.contextPath}/potential-user', '${manager.id}');"></i>
                        </td>
                        </c:forEach>
                </table>
            </div>
        </div>
    </div>

    <div id="driver" class="w3-container w3-border role" style="display: none">
        <h2>Driver</h2>
        <div class="w3-container">
            <div class="w3-section w3-responsive">
                <table class="w3-table-all">
                    <tr class="w3-red">
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Car Number</th>
                        <th>Car Mark</th>
                        <th>Car Model</th>
                        <th>Bodywork</th>
                        <th>Capacity</th>
                        <th>Volume</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${requestScope.drivers}" var="driver">
                        <tr>
                            <td>${driver.key.name}</td>
                            <td>${driver.key.lastname}</td>
                            <td>${driver.key.email}</td>
                            <td>${driver.key.phone}</td>
                            <td>${driver.value.number}</td>
                            <td>${driver.value.mark}</td>
                            <td>${driver.value.model}</td>
                            <td>${driver.value.bodywork.bodywork}</td>
                            <td>${driver.value.maxWeight}</td>
                            <td>${driver.value.maxVolume}</td>
                            <td><i class="fa fa-plus w3-text-green"
                                   onclick="addDriver('${pageContext.request.contextPath}/potential-user', '${driver.key.id}');"></i>
                            </td>
                            <td><i class="fa fa-minus w3-text-red"
                                   onclick="cancelUser('${pageContext.request.contextPath}/potential-user', '${driver.key.id}');"></i>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jspf" %>
<script src="${pageContext.request.contextPath}/scripts/main.js"></script>
<script src="${pageContext.request.contextPath}/scripts/register_potential_user.js"></script>
</body>
</html>
