<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="../mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 02.02.18
  Time: 9:51
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
<ma:nav/>
<h2>GARAGE</h2>
<div class="w3-container w3-padding">
    <c:forEach items="${requestScope.garageCars}" var="car">
        <%--<c:forEach begin="0" end="3">--%>
            <div class="w3-col l3 m6 w3-margin-bottom w3-row-padding w3-blue car-card" style="width: 300px; margin-left: 6px; margin-right: 6px;">
                <div class="w3-display-container" style="margin-left: -8px" onmouseover="">
                    <img src="${pageContext.request.contextPath}/img/gaz3310.jpg" alt="Fjords"
                    <c:if test="${car.car.status.status eq 'broken'}">
                         class="w3-grayscale-max"
                    </c:if>  style="object-fit: cover;width:300px; height: 300px;">
                    <div class="w3-display-topleft w3-black w3-padding" >${car.car.mark} ${car.car.model}</div>
                    <c:if test="${car.car.status.status eq 'broken'}">
                        <div class="w3-display-middle w3-border-red w3-text-red w3-padding w3-text-shadow" style=" border:4px solid"><h1><strong>BROKEN</strong></h1></div>
                    </c:if>
                    <i class="fa fa-gear w3-xxlarge w3-display-topright w3-text-black w3-hover-text-red w3-dropdown-hover">
                        <div class="w3-dropdown-content" style="min-width: auto; background: none;">
                            <h4><i class="fa fa-wrench w3-xxlarge w3-hover-text-white" onclick="changeStatusCar(${car.car.id})"></i></h4>
                            <h4><i class="fa fa-edit w3-xxlarge w3-hover-text-white"></i></h4>

                        </div>
                    </i>

                </div>
                <div  style="margin-top: 20px" class="w3-text-white">
                    <p style="margin-top: -15px"><b>Capacity:</b> ${car.car.maxWeight} <b>Volume:</b> ${car.car.maxVolume}</p>
                    <p style="margin-top: -15px"><b>Bodywork:</b> ${car.car.bodywork.bodywork}</p>
                    <p style="margin-top: -15px"><b>Subscribed:</b> ${car.countSubscribed} <b>Performed:</b> ${car.countPerformed}</p>
                </div>
            </div>
        <%--</c:forEach>--%>
    </c:forEach>

</div>
<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
<script src="${pageContext.request.contextPath}/scripts/garage.js"></script>

<%@include file="/WEB-INF/views/footer.jspf" %>
</body>
</html>
