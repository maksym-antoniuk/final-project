<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 02.02.18
  Time: 9:51
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js" type="text/javascript"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" type="image/png" sizes="16x16">
</head>
<body>
<ma:nav/>
<!-- MODAL UPLOAD -->
<div id="uploadModal" class="w3-modal">
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal" onclick="this.parentElement.parentElement.parentElement.style.display = 'none'">&times;</span>
            <h2><fmt:message key="upload.img"/></h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" id="formUp" method="post" enctype="multipart/form-data">
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
<h2><fmt:message key="nav.garage"/>
    <i class="fa fa-gear w3-xxlarge w3-right w3-text-black w3-dropdown-hover" style="background: none">
        <div class="w3-dropdown-content" style="min-width: auto; background: none;">
            <h4><i class="fa fa-plus w3-xxlarge w3-hover-text-green" onclick="document.getElementById('car-modal').style.display = 'block'"></i></h4>
        </div>
    </i>
</h2>
<div class="w3-container w3-padding">
    <c:forEach items="${requestScope.garageCars}" var="car">
        <%--<c:forEach begin="0" end="3">--%>
            <div class="w3-col l3 m6 w3-margin-bottom w3-row-padding w3-red car-card" style="width: 300px; margin-left: 6px; margin-right: 6px;">
                <div class="w3-display-container w3-red" style="margin-left: -8px" onmouseover="">
                    <img src="${pageContext.request.contextPath}/resources/image/car/${car.car.id}.jpg" alt="Fjords" onerror="this.src = '${pageContext.request.contextPath}/img/defaultcar.jpg'"
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
                            <h4><i class="fa fa-camera-retro w3-xxlarge w3-hover-text-white" onclick="document.getElementById('uploadModal').style.display = 'block';$('#formUp').attr('action', '${pageContext.request.contextPath}/resources/image/car/${car.car.id}');"></i></h4>

                        </div>
                    </i>

                </div>
                <div  style="margin-top: 20px" class="w3-text-white">
                    <p style="margin-top: -15px"><b><fmt:message key="label.car.capacity"/>:</b> ${car.car.maxWeight} <b><fmt:message key="label.car.volume"/>:</b> ${car.car.maxVolume}</p>
                    <p style="margin-top: -15px"><b><fmt:message key="label.type.bodywork"/>:</b> ${car.car.bodywork.bodywork}</p>
                    <p style="margin-top: -15px"><b><fmt:message key="label.subscribed"/>:</b> ${car.countSubscribed} <b><fmt:message key="label.performed"/>:</b> ${car.countPerformed}</p>
                    <p style="margin-top: -15px"><b><fmt:message key="label.sumMoney"/>:</b> ${car.sum}</p>
                </div>
            </div>
        <%--</c:forEach>--%>
    </c:forEach>
</div>

<!-- CREATE CAR MODAL -->

<div id="car-modal" class="w3-modal"
     <c:if test="${requestScope.createCarError ne null}">style="display: block" </c:if> >
    <div class="w3-modal-content w3-animate-top">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="close-login-modal" onclick="this.parentElement.parentElement.parentElement.style.display = 'none'">&times;</span>
            <h2><fmt:message key="label.add.car"/></h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" action="${pageContext.request.contextPath}/car" method="post">
                <div class="w3-section">
                    <c:if test="${requestScope.createCarError != null}">
                        <div class="w3-panel w3-redd w3-display-container">
                            <span onclick="this.parentElement.style.display='none'"
                                  class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                            <c:forEach var="error" items="${requestScope.createCarError}">
                                <h3>${error.key}</h3>
                                <p>${error.value}</p>
                            </c:forEach>
                        </div>
                    </c:if>
                    <input hidden name="create" value="a">
                    <label><fmt:message key="label.car.mark"/></label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.mark"/>"
                           id="car_mark" name="car_mark" required value="${dto.carMark}" >
                    <label><fmt:message key="label.car.model"/></label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.model"/>"
                           id="car_model" name="car_model" required value="${dto.carModel}"  >
                    <label><fmt:message key="label.car.number"/></label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text"
                           placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.number"/>" id="car_number" name="car_number" required value="${dto.carNumber}">
                    <label><fmt:message key="label.type.bodywork"/></label>
                    <select class="w3-select" name="type_bodywork" required>
                        <option value="" disabled selected><fmt:message key="label.placeholder.bodywork"/></option>
                        <option value="tank"><fmt:message key="bodywork.tank"/></option>
                        <option value="bulk"><fmt:message key="bodywork.bulk"/></option>
                        <option value="animal"><fmt:message key="bodywork.animal"/></option>
                        <option value="container"><fmt:message key="bodywork.container"/></option>
                        <option value="car"><fmt:message key="bodywork.car"/></option>
                    </select>
                    <br>
                    <br>
                    <label><fmt:message key="label.car.capacity"/></label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text"
                           placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.capacity"/>" id="capacity" name="capacity" required value="${dto.capacity}" >
                    <label><fmt:message key="label.car.volume"/></label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text"
                           placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.volume"/>" id="volume" name="volume" required value="${dto.volume}" >
                    <button class="w3-black w3-btn-block w3-section w3-padding"><fmt:message key="input.submit"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:if test="${requestScope.errorUpload != null}">
    <div id="driverCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
            <c:if test="${requestScope.errorUpload != ''}">
                <div class="w3-panel w3-redd w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                    <h3>Danger!</h3>
                    <p>${requestScope.errorUpload}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.errorUpload == ''}">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Image has changed</p>
                </div>
            </c:if>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.errorStatusCar != null}">
    <div id="driverCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
            <c:if test="${requestScope.errorStatusCar != ''}">
                <div class="w3-panel w3-redd w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                    <h3>Danger!</h3>
                    <p>${requestScope.errorStatusCar}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.errorStatusCar == ''}">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Status has changed</p>
                </div>
            </c:if>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.goodCreate != null}">
    <div id="driverCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Thank you for making a request for adding a car!</p>
                </div>
        </div>
    </div>
</c:if>

<script src="${pageContext.request.contextPath}/scripts/js/custom-file-input.js"></script>
<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
<script src="${pageContext.request.contextPath}/scripts/garage.js"></script>

<%@include file="/WEB-INF/views/footer.jspf" %>
</body>
</html>
