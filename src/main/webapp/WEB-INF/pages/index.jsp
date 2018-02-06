<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/start.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/${sessionScope.theme}.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<%@include file="../views/sign.jspf" %>
<ma:nav/>

<!-- SHOWCASE -->
<section class="showcase">
    <div class="w3-container w3-center">
        <h1 class="w3-text-shadow w3-animate-opacity"><fmt:message key="index.company"/></h1>
        <hr>
        <p class="w3-animate-opacity"><fmt:message key="index.about"/></p>
        <button class="w3-button w3-red w3-large w3-opacity" id="showcaseButton"><fmt:message key="index.start"/></button>
    </div>
</section>
<section class="section w3-red w3-hover-opacity">
    <div class="w3-container w3-center">
        <i class="fa fa-home"></i>
        <h2><fmt:message key="index.welcome"/></h2>
        <p><fmt:message key="index.requirement"/></p>
    </div>
</section>

<!-- ABOUT -->
<section id="about" class="section">
    <div class="w3-container">
        <div class="w3-row-padding">
            <div class="w3-col m5">
                <img src="img/about.jpg">
            </div>
            <div class="w3-col m7">
                <button onclick="accFunction('driverQ')" class="w3-btn-block w3-left-align">
                    <fmt:message key="driver"/>
                </button>

                <div id="driverQ" class="w3-container w3-show">
                    <h3><fmt:message key="index.req.driver.head"/></h3>
                    <p><fmt:message key="index.req.driver.body"/></p>
                </div>

                <button onclick="accFunction('managerQ')" class="w3-btn-block w3-left-align">
                    <fmt:message key="manager"/>
                </button>

                <div id="managerQ" class="w3-container w3-hide">
                    <h3><fmt:message key="index.req.manager.head"/></h3>
                    <p><fmt:message key="index.req.manager.body"/></p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- SERVICES HEADING -->
<section id="services" class="section w3-red w3-hover-opacity">
    <div class="w3-container w3-center">
        <h1 class="w3-text-shadow"><fmt:message key="index.type"/> </h1>
        <p><fmt:message key="index.type.body"/></p>
    </div>
</section>

<!-- SERVICES -->
<section class="section w3-light-grey">
    <div class="w3-container w3-center">
        <div class="w3-row w3-center">
            <div class="w3-col m3  w3-center">
                <i class="fa fa-linux w3-red w3-padding-small w3-round-xlarge"></i>
                <h3><fmt:message key="animal"/></h3>
                <p><fmt:message key="index.animal.body"/></p>
            </div>
            <div class="w3-col m3  w3-center">
                <i class="fa fa-truck w3-red w3-padding-small w3-round-xlarge"></i>
                <h3><fmt:message key="bulk"/> & <fmt:message key="solid"/></h3>
                <p><fmt:message key="index.solid.body"/></p>
            </div>
            <div class="w3-col m3  w3-center">
                <i class="fa fa-car w3-red w3-padding-small w3-round-xlarge"></i>
                <h3><fmt:message key="car"/></h3>
                <p><fmt:message key="index.car.body"/></p>
            </div>
            <div class="w3-col m3  w3-center">
                <i class="fa fa-flask w3-red w3-padding-small w3-round-xlarge"></i>
                <h3><fmt:message key="liquid"/></h3>
                <p><fmt:message key="index.liquid.body"/></p>
            </div>
        </div>
    </div>
</section>


<c:if test="${requestScope.reg_error != null}">
    <c:if test="${requestScope.reg_error eq ''}">
        <div class="w3-modal" style="display: block">
            <div class="w3-modal-content w3-animate-zoom">
                <div class="w3-panel w3-green w3-display-container">
                <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                      class="w3-button w3-green w3-large w3-display-topright">&times;</span>
                    <h3><fmt:message key="message.good"/></h3>
                    <p><fmt:message key="message.registration.good"/></p>
                </div>
            </div>
        </div>
    </c:if>
</c:if>

<!-- MODAL -->
<div id="start-modal" class="w3-modal"
     <c:if test="${requestScope.isRegister != null}">style="display: block" </c:if>>
    <div class="w3-modal-content w3-animate-bottom" id="modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal">&times;</span>
            <h2><fmt:message key="index.getstarted"/></h2>
        </header>
        <div class="w3-container" id="mytabs">
            <h2><fmt:message key="index.chooserole"/> </h2>
            <p><fmt:message key="index.choose.body"/> </p>

            <div class="w3-bar w3-black">
                <button class="w3-bar-item w3-button tablink w3-red" onclick="openRole(event,'manager')"><fmt:message key="manager"/></button>
                <button class="w3-bar-item w3-button tablink" onclick="openRole(event,'driver')"><fmt:message key="driver"/> </button>
            </div>
            <c:if test="${regErrors != null}">
            <div class="w3-panel w3-redd w3-display-container">
                <span onclick="this.parentElement.style.display='none'"
                      class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                <c:forEach var="error" items="${regErrors}">
                    <h3>${error.key}</h3>
                    <p>${error.value}</p>
                </c:forEach>
            </div>
            </c:if>

            <div id="manager" class="w3-container w3-border role"
                 <c:if test="${requestScope.role eq 'manager' || !(requestScope.role eq 'driver')}">style="display: block" </c:if>
                 <c:if test="${requestScope.role eq 'driver'}">style="display: none" </c:if>>
                <h2><fmt:message key="manager"/> </h2>
                <div class="w3-container">
                    <form method="post" action="registration">
                        <div class="w3-section">
                            <input type="hidden" name="role" value="manager">
                            <label><fmt:message key="label.name"/> </label>
                            <input pattern="(([A-Z]){0,1}[a-z]+)|(([А-ЯЁ]){0,1}[а-яё]+)" minlength="4" maxlength="10" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.name"/>"
                                   id="manager_name" name="manager_name" required
                            <c:if test="${role eq 'manager'}"> value="${dto.name}" </c:if> >
                            <label><fmt:message key="label.surname"/></label>
                            <input pattern="(([A-Z]){0,1}[a-z]+)|(([А-ЯЁ]){0,1}[а-яё]+)" minlength="4" maxlength="10" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.surname"/>"
                                   id="manager_surname" name="manager_surname" required
                            <c:if test="${role eq 'manager'}"> value="${dto.surname}" </c:if> >
                            <label><fmt:message key="label.email"/></label>
                            <input class="w3-input w3-border w3-margin-bottom" type="email" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.email"/>"
                                   id="manager_email" name="manager_email" required
                            <c:if test="${role eq 'manager'}"> value="${dto.email}" </c:if> >
                            <label><fmt:message key="label.phone"/></label>
                            <input pattern="[0-9]+" minlength="10" maxlength="10" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.phone"/>"
                                   id="manager_phone" name="manager_phone" required
                            <c:if test="${role eq 'manager'}"> value="${dto.phone}" </c:if> >
                            <button class="w3-black w3-btn-block w3-section w3-padding"><fmt:message key="input.submit"/></button>
                        </div>
                    </form>
                </div>
            </div>

            <div id="driver" class="w3-container w3-border role" <c:if
                    test="${requestScope.role eq 'manager' || !(role eq 'driver')}"> style="display: none" </c:if>>
                <h2><fmt:message key="driver"/> </h2>
                <div class="w3-container">
                    <form method="post" action="registration">
                        <div class="w3-section">
                            <input type="hidden" name="role" value="driver">
                            <label><fmt:message key="label.name"/></label>
                            <input pattern="(([A-Z]){0,1}[a-z]+)|(([А-ЯЁ]){0,1}[а-яё]+)" minlength="4" maxlength="10" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.name"/>"
                                   id="driver_name" name="driver_name" required
                            <c:if test="${role eq 'driver'}"> value="${dto.name}" </c:if> >
                            <label><fmt:message key="label.surname"/></label>
                            <input pattern="(([A-Z]){0,1}[a-z]+)|(([А-ЯЁ]){0,1}[а-яё]+)" minlength="4" maxlength="10" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.surname"/>"
                                   id="driver_surname" name="driver_surname" required
                            <c:if test="${role eq 'driver'}"> value="${dto.surname}" </c:if> >
                            <label><fmt:message key="label.email"/></label>
                            <input class="w3-input w3-border w3-margin-bottom" type="email" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.email"/>"
                                   id="driver_email" name="driver_email" required
                            <c:if test="${role eq 'driver'}"> value="${dto.email}" </c:if> >
                            <label><fmt:message key="label.phone"/></label>
                            <input pattern="[0-9]+" minlength="10" maxlength="10" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.phone"/>"
                                   id="driver_phone" name="driver_phone" required
                            <c:if test="${role eq 'driver'}"> value="${dto.phone}" </c:if> >
                            <label><fmt:message key="label.car.mark"/></label>
                            <input pattern="(([A-Za-z0-9\-]+)|([А-ЯЁа-яё0-9\-]+)" minlength="3" maxlength="15" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.mark"/>"
                                   id="car_mark" name="car_mark" required
                            <c:if test="${role eq 'driver'}"> value="${dto.carMark}" </c:if> >
                            <label><fmt:message key="label.car.model"/></label>
                            <input pattern="(([A-Za-z0-9\-]+)|([А-ЯЁа-яё0-9\-]+)" minlength="3" maxlength="15"class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.model"/>"
                                   id="car_model" name="car_model" required
                            <c:if test="${role eq 'driver'}"> value="${dto.carModel}" </c:if> >
                            <label><fmt:message key="label.car.number"/></label>
                            <input pattern="(([a-zA-Z0-9\-]+)|([А-ЯЁа-яё0-9\-]+)" minlength="5" maxlength="9" class="w3-input w3-border w3-margin-bottom" type="text"
                                   placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.number"/>" id="car_number" name="car_number" required
                            <c:if test="${role eq 'driver'}"> value="${dto.carNumber}" </c:if> >
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
                            <input pattern="[0-9]+|([0-9]\.[0-9]+)" minlength="1" maxlength="11" class="w3-input w3-border w3-margin-bottom" type="text"
                                   placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.capacity"/>" id="capacity" name="capacity" required
                            <c:if test="${role eq 'driver'}"> value="${dto.capacity}" </c:if> >
                            <label><fmt:message key="label.car.volume"/></label>
                            <input pattern="[0-9]+|([0-9]\.[0-9]+)" minlength="1" maxlength="11" class="w3-input w3-border w3-margin-bottom" type="text"
                                   placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.car.volume"/>" id="volume" name="volume" required
                            <c:if test="${role eq 'driver'}"> value="${dto.volume}" </c:if> >
                            <button class="w3-black w3-btn-block w3-section w3-padding"><fmt:message key="input.submit"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- LOGIN MODAL -->

<div id="login-modal" class="w3-modal"
     <c:if test="${requestScope.isLogin != null}">style="display: block" </c:if> >
    <div class="w3-modal-content w3-animate-top">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="close-login-modal">&times;</span>
            <h2><fmt:message key="label.signin"/></h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" action="login" method="post">
                <div class="w3-section">
                    <c:if test="${loginErrors != null}">
                        <div class="w3-panel w3-redd w3-display-container">
                            <span onclick="this.parentElement.style.display='none'"
                                class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                            <c:forEach var="error" items="${loginErrors}">
                                <h3>${error.key}</h3>
                                <p>${error.value}</p>
                            </c:forEach>


                        </div>
                    </c:if>
                    <label><fmt:message key="label.email"/></label>
                    <input class="w3-input w3-border w3-margin-bottom" type="email" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.email"/>"
                           id="login_email" name="login_email" required>
                    <label><fmt:message key="label.password"/></label>
                    <input pattern="[a-zA-Z0-9]{3,16}" minlength="3" maxlength="16" class="w3-input w3-border w3-margin-bottom" type="password" placeholder="<fmt:message key="label.placeholder.enter"/> <fmt:message key="label.password"/>"
                           id="login_password" name="login_password" required>
                    <button class="w3-black w3-btn-block w3-section w3-padding"><fmt:message key="input.submit"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/scripts/main.js"></script>
<%@include file="/WEB-INF/views/footer.jspf" %>
</body>
</html>
