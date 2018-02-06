<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 03.02.18
  Time: 21:37
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/upload.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js" type="text/javascript"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" type="image/png" sizes="16x16">
</head>
<body>
<ma:nav/>
<div>
    <img src="${pageContext.request.contextPath}/resources/image/user/${sessionScope.user.id}.jpg" alt="Fjords" onerror="this.src = '${pageContext.request.contextPath}/img/default-user.png'"
             style="object-fit: cover;width:300px; height: 300px;">
    <i class="fa fa-gear w3-xxlarge w3-right w3-text-black w3-hover-text-red w3-dropdown-hover">
        <div class="w3-dropdown-content" style="min-width: auto; background: none;">
            <h4><i class="fa fa-edit w3-xxlarge w3-hover-text-white" onclick="document.getElementById('passModal').style.display = 'block';"></i></h4>
            <h4><i class="fa fa-camera-retro w3-xxlarge w3-hover-text-red" onclick="document.getElementById('uploadModal').style.display = 'block';"></i></h4>
        </div>
    </i>
    <table class="w3-table">
        <tr><td style="width: auto"><b><fmt:message key="label.name"/>:</b> ${requestScope.portfolio.username}</td></tr>
        <tr><td style="width: auto"><b><fmt:message key="label.surname"/>:</b> ${requestScope.portfolio.surname}</td></tr>
        <tr><td style="width: auto"><b><fmt:message key="col.role"/>:</b> ${sessionScope.user.role.role}</td></tr>
        <tr><td style="width: auto"><b><fmt:message key="label.email"/>:</b> ${requestScope.portfolio.email}</td></tr>
        <tr><td style="width: auto"><b><fmt:message key="label.phone"/>:</b> ${requestScope.portfolio.phone}</td></tr>
        <tr><td style="width: auto"><b><fmt:message key="label.days"/>:</b> ${requestScope.portfolio.days}</td></tr>
        <tr><td style="width: auto"><b><fmt:message key="label.count.journeys"/>:</b> ${requestScope.portfolio.countJourney}</td></tr>
        <c:if test="${sessionScope.user.role.role eq 'driver'}">
            <tr><td><b><fmt:message key="label.count.car"/>:</b> ${sessionScope.driverCars.size()}</td></tr>
        </c:if>

    </table>
</div>

<!-- MODAL UPLOAD -->
<div id="uploadModal" class="w3-modal">
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal" onclick="this.parentElement.parentElement.parentElement.style.display = 'none'">&times;</span>
            <h2><fmt:message key="upload.img"/></h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" id="formUp" method="post" action="${pageContext.request.contextPath}/resources/image/user/${sessionScope.user.id}" enctype="multipart/form-data">
                <div class="w3-section">
                    <input name="file" id="file-5" class="inputfile inputfile-4" style="opacity: 0" data-multiple-caption="{count} files selected" multiple="" type="file" accept="image/* ">
                    <label for="file-5"><i class="fa fa-upload w3-xxxlarge w3-row"></i> <span><fmt:message key="choose.file"/></span></label>
                    <div id="uic" name="uploadImgCar" value="5">
                        <input class="w3-quarter" style="opacity: 0" >
                        <button class="w3-black w3-btn-block w3-section w3-padding w3-half w3-center"><fmt:message key="input.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<c:if test="${requestScope.errorChangePassword != null}">
    <c:if test="${requestScope.errorChangePassword eq ''}">
        <div class="w3-modal" style="display: block">
            <div class="w3-modal-content w3-animate-zoom">
                <div class="w3-panel w3-green w3-display-container">
                <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                      class="w3-button w3-green w3-large w3-display-topright">&times;</span>
                    <h3><fmt:message key="message.good"/></h3>
                    <p>Password changed</p>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${requestScope.errorChangePassword ne ''}">
        <div class="w3-modal" style="display: block">
            <div class="w3-modal-content w3-animate-zoom">
                <div class="w3-panel w3-redd w3-display-container">
                <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                      class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                    <h3><fmt:message key="message.danger"/></h3>
                    <p>${requestScope.errorChangePassword}</p>
                </div>
            </div>
        </div>
    </c:if>
</c:if>

<div id="passModal" class="w3-modal">
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" onclick="this.parentElement.parentElement.parentElement.style.display = 'none'">&times;</span>
            <h2><fmt:message key="upload.img"/></h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" method="post" onsubmit="return isEqualsPasswords()" action="${pageContext.request.contextPath}/user/password" enctype="multipart/form-data">
                <div class="w3-section">
                    <c:if test="${errorChangePassword != null}">
                        <div class="w3-panel w3-redd w3-display-container">
                            <span onclick="this.parentElement.style.display='none'"
                                  class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                            <c:forEach var="error" items="${erroeChangePassword}">
                                <h3>${error.key}</h3>
                                <p>${error.value}</p>
                            </c:forEach>
                        </div>
                    </c:if>
                    <label><fmt:message key="label.password"/></label>
                    <input pattern="[a-zA-Z0-9]{3,16}" minlength="3" maxlength="16" class="w3-input w3-border w3-margin-bottom" type="password" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.password"/>"
                           id="pass1" name="pass" required>
                    <label><fmt:message key="col.confirm"/> <fmt:message key="label.password"/></label>
                    <input pattern="[a-zA-Z0-9]{3,16}" minlength="3" maxlength="16" class="w3-input w3-border w3-margin-bottom" type="password" placeholder="<fmt:message key="col.confirm"/> <fmt:message key="label.password"/>"
                           id="pass2" name="confirmPass" required>
                    <button class="w3-black w3-btn-block w3-section w3-padding"><fmt:message key="input.submit"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jspf" %>

<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
<script src="${pageContext.request.contextPath}/scripts/js/custom-file-input.js"></script>
</body>
</html>
