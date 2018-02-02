<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/start.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/w31.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<%@include file="../views/sign.jspf" %>


<!-- SHOWCASE -->
<section class="showcase">
    <div class="w3-container w3-center">
        <h1 class="w3-text-shadow w3-animate-opacity">Go Anywhere</h1>
        <hr>
        <p class="w3-animate-opacity">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo</p>
        <button class="w3-button w3-red w3-large w3-opacity" id="showcaseButton">Start Here</button>
    </div>
</section>
<section class="section w3-red w3-hover-opacity">
    <div class="w3-container w3-center">
        <i class="fa fa-home"></i>
        <h2>Welcome Home</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
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
                    What We Do
                </button>

                <div id="driverQ" class="w3-container w3-show">
                    <h3>Требования к водителю</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                        laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                        voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
                        non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>

                <button onclick="accFunction('managerQ')" class="w3-btn-block w3-left-align">
                    What We Do
                </button>

                <div id="managerQ" class="w3-container w3-hide">
                    <h3>Требования к менеджеру</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                        laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                        voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- SERVICES HEADING -->
<section id="services" class="section w3-red w3-hover-opacity">
    <div class="w3-container w3-center">
        <h1 class="w3-text-shadow">Виды перевозок</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua.</p>
    </div>
</section>

<!-- SERVICES -->
<section class="section w3-light-grey">
    <div class="w3-container w3-center">
        <div class="w3-row">
            <div class="w3-col m3">
                <i class="fa fa-comment w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>Internet Marketing</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
            <div class="w3-col m3">
                <i class="fa fa-search w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>SEO</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
            <div class="w3-col m3">
                <i class="fa fa-cubes w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>Software Development</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
            <div class="w3-col m3">
                <i class="fa fa-cloud w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>Cloud Hosting</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER -->


<!-- MODAL -->
<div id="start-modal" class="w3-modal"
     <c:if test="${requestScope.isRegister != null}">style="display: block" </c:if>>
    <div class="w3-modal-content w3-animate-bottom" id="modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal">&times;</span>
            <h2>Get Started</h2>
        </header>
        <div class="w3-container" id="mytabs">
            <h2>Active Tabs</h2>
            <p>To highlight the current tab/page the user is on, add a color class, and use JavaScript to update the
                active link.</p>

            <div class="w3-bar w3-black">
                <button class="w3-bar-item w3-button tablink w3-red" onclick="openRole(event,'manager')">Manager
                </button>
                <button class="w3-bar-item w3-button tablink" onclick="openRole(event,'driver')">Driver</button>
            </div>
            <c:if test="${regErrors != null}">
            <div class="w3-panel w3-red w3-display-container">
                <span onclick="this.parentElement.style.display='none'"
                      class="w3-button w3-red w3-large w3-display-topright">&times;</span>
                <c:forEach var="error" items="${regErrors}">
                    <h3>${error.key}</h3>
                    <p>${error.value}</p>
                </c:forEach>
            </div>
            </c:if>

            <div id="manager" class="w3-container w3-border role"
                 <c:if test="${requestScope.role eq 'manager' || !(requestScope.role eq 'driver')}">style="display: block" </c:if>
                 <c:if test="${requestScope.role eq 'driver'}">style="display: none" </c:if>>
                <h2>Manager</h2>
                <div class="w3-container">
                    <form method="post" action="registration">
                        <div class="w3-section">
                            <input type="hidden" name="role" value="manager">
                            <label>Name</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Name"
                                   id="manager_name" name="manager_name" required
                            <c:if test="${role eq 'manager'}"> value="${dto.name}" </c:if> >
                            <label>Surname</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Surname"
                                   id="manager_surname" name="manager_surname" required
                            <c:if test="${role eq 'manager'}"> value="${dto.surname}" </c:if> >
                            <label>Email</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="email" placeholder="Enter Email"
                                   id="manager_email" name="manager_email" required
                            <c:if test="${role eq 'manager'}"> value="${dto.email}" </c:if> >
                            <label>Phone</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Phone"
                                   id="manager_phone" name="manager_phone" required
                            <c:if test="${role eq 'manager'}"> value="${dto.phone}" </c:if> >
                            <button class="w3-black w3-btn-block w3-section w3-padding">Submit</button>
                        </div>
                    </form>
                </div>
            </div>

            <div id="driver" class="w3-container w3-border role" <c:if
                    test="${requestScope.role eq 'manager' || !(role eq 'driver')}"> style="display: none" </c:if>>
                <h2>Driver</h2>
                <div class="w3-container">
                    <form method="post" action="registration">
                        <div class="w3-section">
                            <input type="hidden" name="role" value="driver">
                            <label>Name</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Name"
                                   id="driver_name" name="driver_name" required
                            <c:if test="${role eq 'driver'}"> value="${dto.name}" </c:if> >
                            <label>Surname</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Surname"
                                   id="driver_surname" name="driver_surname" required
                            <c:if test="${role eq 'driver'}"> value="${dto.surname}" </c:if> >
                            <label>Email</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="email" placeholder="Enter Email"
                                   id="driver_email" name="driver_email" required
                            <c:if test="${role eq 'driver'}"> value="${dto.email}" </c:if> >
                            <label>Phone</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Phone"
                                   id="driver_phone" name="driver_phone" required
                            <c:if test="${role eq 'driver'}"> value="${dto.phone}" </c:if> >
                            <label>Car Mark</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Car Mark"
                                   id="car_mark" name="car_mark" required
                            <c:if test="${role eq 'driver'}"> value="${dto.carMark}" </c:if> >
                            <label>Car Model</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Car Model"
                                   id="car_model" name="car_model" required
                            <c:if test="${role eq 'driver'}"> value="${dto.carModel}" </c:if> >
                            <label>Car Number</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text"
                                   placeholder="Enter Car Number" id="car_number" name="car_number" required
                            <c:if test="${role eq 'driver'}"> value="${dto.carNumber}" </c:if> >
                            <label>Type Bodywork</label>
                            <select class="w3-select" name="type_bodywork" required>
                                <option value="" disabled selected>Choose your type bodywork</option>
                                <option value="tank">Tank</option>
                                <option value="bulk">Bulk</option>
                                <option value="animal">Animal</option>
                                <option value="container">Container</option>
                                <option value="car">Car</option>
                            </select>
                            <br>
                            <br>
                            <label>Carrying capacity</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text"
                                   placeholder="Enter Carrying Capacity" id="capacity" name="capacity" required
                            <c:if test="${role eq 'driver'}"> value="${dto.capacity}" </c:if> >
                            <label>Max Volume</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text"
                                   placeholder="Enter Max Volume" id="volume" name="volume" required
                            <c:if test="${role eq 'driver'}"> value="${dto.volume}" </c:if> >
                            <button class="w3-black w3-btn-block w3-section w3-padding">Submit</button>
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
            <h2>Sign In</h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" action="login" method="post">
                <div class="w3-section">
                    <c:if test="${loginErrors != null}">
                        <div class="w3-panel w3-red w3-display-container">
                            <span onclick="this.parentElement.style.display='none'"
                                class="w3-button w3-red w3-large w3-display-topright">&times;</span>
                            <c:forEach var="error" items="${loginErrors}">
                                <h3>${error.key}</h3>
                                <p>${error.value}</p>
                            </c:forEach>


                        </div>
                    </c:if>
                    <label>Email</label>
                    <input class="w3-input w3-border w3-margin-bottom" type="email" placeholder="Enter Email"
                           id="login_email" name="login_email" required>
                    <label>Password</label>
                    <input class="w3-input w3-border w3-margin-bottom" type="password" placeholder="Enter Password"
                           id="login_password" name="login_password" required>
                    <button class="w3-black w3-btn-block w3-section w3-padding">Sign In</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/scripts/main.js"></script>
</body>
</html>
