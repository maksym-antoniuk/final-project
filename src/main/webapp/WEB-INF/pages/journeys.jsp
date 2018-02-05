<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="../mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
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
<h2>JOURNEYS
    <div class="w3-dropdown-hover w3-hover-none w3-hover-text-red w3-white">
        <i class="fa fa-gear"><i class="fa fa-caret-down w3-large" style="margin-left: -2px;"></i></i>
        <div id="editPanel" class="w3-dropdown-content w3-bar-block w3-border">
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <h6><i onclick="openModalAdd();" class="fa fa-plus w3-text-light-green w3-large"></i> Add journey</h6>
            </c:if>
            <h6><i id="editJourney" class="fa fa-edit w3-text-dark-gray w3-large" style="margin-top: -10px"></i> Edit Panel</h6>
            <h6>Rows: <i id="sendCountRows" class="fa fa-play w3-xlarge w3-text-blue w3-right">  </i><input id="countRows" class="w3-border w3-input w3-half w3-right" type="number" style="margin-top: -10px"></h6>
            <h6><input id="sort_user" class="w3-left" type="checkbox" <c:if test="${sessionScope.filterJourney.onlyMy == true}">checked</c:if>><label>Only My</label></h6>
        </div>
    </div>
</h2>
<c:if test="${sessionScope.user.role.role ne 'driver'}">
    <div class="w3-panel w3-pale-green w3-border w3-round-large w3-border-green">
        <h3>Edit panel</h3>
        <h4>


        </h4>
    </div>
</c:if>
<div class="w3-responsive">
    <table class="w3-table-all">
        <tr class="w3-red">
            <th class="w3-center">Id <i id="sort_id"
                                        class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascId == true}">fa fa-sort-numeric-asc</c:if> <c:if test="${sessionScope.filterJourney.ascId == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascId == false}">fa fa-sort-numeric-desc</c:if>"></i>
            </th>
            <th class="w3-center">Date <i id="sort_date"
                                          class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascDateStart == true}">fa fa-sort-amount-desc</c:if> <c:if test="${sessionScope.filterJourney.ascDateStart == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascDateStart == false}">fa fa-sort-amount-asc</c:if>"></i>
            </th>
            <th class="w3-center">Status <i
                    class="fa fa-filter w3-xlarge w3-text-white w3-hover-text-black w3-dropdown-hover"
                    style="background: none">
                <div class="w3-dropdown-content">
                    <h4><input id="sort_new" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'new'}"> checked</c:if></c:forEach> type="checkbox"><label>New</label></h4>
                    <h4><input id="sort_perform" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'on_process'}"> checked</c:if> </c:forEach>type="checkbox"><label>Perform</label></h4>
                    <h4><input id="sort_old" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'old'}"> checked</c:if> </c:forEach>type="checkbox"><label>Old</label></h4>
                    <h4><input id="sort_cancel" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'canceled'}"> checked </c:if></c:forEach>type="checkbox"><label>Cancel</label></h4>
                </div>
            </i>
            </th>
            <th class="w3-center">Price <i id="sort_price"
                                           class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascPrice == true}">fa fa-sort-amount-asc</c:if> <c:if test="${sessionScope.filterJourney.ascPrice == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascPrice == false}">fa fa-sort-amount-desc</c:if>"></i></th>
            <th class="w3-center">Weight <i id="sort_capacity"
                                            class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascCapacity == true}">fa fa-sort-amount-asc</c:if> <c:if test="${sessionScope.filterJourney.ascCapacity == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascCapacity == false}">fa fa-sort-amount-desc</c:if>"></i></th>
            <th class="w3-center">Material <i
                    class="fa fa-filter w3-xlarge w3-text-white w3-hover-text-black w3-dropdown-hover"
                    style="background: none">
                <div class="w3-dropdown-content">
                    <h4><input id="sort_bulk" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'bulk'}"> checked</c:if></c:forEach> type="checkbox"><label>Bulk</label></h4>
                    <h4><input id="sort_solid" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'solid'}"> checked</c:if> </c:forEach>type="checkbox"><label>Solid</label></h4>
                    <h4><input id="sort_liquid" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'liquid'}"> checked</c:if> </c:forEach>type="checkbox"><label>Liquid</label></h4>
                    <h4><input id="sort_car" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'car'}"> checked </c:if></c:forEach>type="checkbox"><label>Car</label></h4>
                    <h4><input id="sort_animal" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'animal'}"> checked </c:if></c:forEach>type="checkbox"><label>Animal</label></h4>
                </div>
            </i></th>
            <th class="w3-center">Volume <i id="sort_volume"
                                            class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascVolume == true}">fa fa-sort-amount-asc</c:if> <c:if test="${sessionScope.filterJourney.ascVolume == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascVolume == false}">fa fa-sort-amount-desc</c:if>"></i></th>
            <th class="w3-center">Map</th>
            <c:if test="${sessionScope.user.role.role eq 'admin'}">
                <th class="w3-center editRow" style="display:none;">Manager</th>
            </c:if>
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <th style="width: 0px"></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'admin' || sessionScope.user.role.role eq 'manager'}">
                <th class="w3-center editRow" style="display:none;">Car</th>
            </c:if>
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <th style="width: 0px"></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <th class="w3-center editRow" style="display:none;">Cancel</th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'driver'}">
                <th class="w3-center editRow" style="display:none;">Subscribe</th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'driver'}">
                <th style="width: 0px"></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'driver'}">
                <th class="w3-center editRow" style="display:none;">Confirm</th>
            </c:if>
        </tr>
        <c:forEach items="${requestScope.journeys}" var="journey">
            <tr>
                <td class="w3-center">${journey.journey.id}</td>
                <td class="w3-center">${journey.journey.date}</td>
                <td class="w3-center"><ma:status category="${journey.journey.status.status}"/></td>
                <td class="w3-center">${journey.journey.price}</td>
                <td class="w3-center">${journey.journey.weight}</td>
                <td class="w3-center">${journey.journey.material.material}</td>
                <td class="w3-center">${journey.journey.volume}</td>
                <td class="w3-center"><i class="fa fa-map-o w3-large"
                                         onclick="openMap('${journey.journey.from}', '${journey.journey.where}');"></i>
                </td>
                <c:if test="${sessionScope.user.role.role eq 'admin'}">
                    <td class="portfolioButton w3-center editRow" style="display:none;"
                        portfolioid="${journey.journey.idManager}"><i class="fa fa-vcard-o w3-large"></i></td>
                </c:if>
                <c:if test="${sessionScope.user.role.role ne 'driver'}">
                    <td style="width: 0px"></td>
                </c:if>
                <c:if test="${sessionScope.user.role.role eq 'admin' || sessionScope.user.role.role eq 'manager'}">
                    <td class="w3-center editRow" style="display:none;">
                        <c:if test="${sessionScope.user.role.role eq 'admin' || sessionScope.user.role.role eq 'manager' && journey.idManager eq sessionScope.user.id}">
                            <i class="fa fa-car w3-large" onclick="acceptCar(${journey.journey.id})"> <span
                                    class="w3-badge w3-red w3-small w3-circle"
                                    style="margin-left:-10px; margin-top:-20px; padding-right:3px; padding-left:3px;">${journey.countCar}</span></i>
                        </c:if>
                    </td>
                </c:if>
                <c:if test="${sessionScope.user.role.role ne 'driver'}">
                    <td style="width: 0px"></td>
                </c:if>
                <c:if test="${sessionScope.user.role.role ne 'driver'}">
                    <td class="w3-center editRow" style="display:none;">
                        <c:if test="${journey.journey.status.status eq 'new'}">
                            <c:if test="${sessionScope.user.role.role eq 'admin' || sessionScope.user.role.role eq 'manager' && sessionScope.user.id eq journey.idManager}">
                                <i class="fa fa-times w3-text-red w3-large"
                                   onclick="cancelJourney(${journey.journey.id})"></i>
                            </c:if>
                        </c:if>
                    </td>
                </c:if>
                <c:if test="${sessionScope.user.role.role eq 'driver'}">
                    <td driverid="${sessionScope.user.id}" class="driverCars w3-center editRow" style="display:none;">
                        <c:if test="${journey.journey.status.status eq 'new'}">
                            <i class="fa fa-share w3-large"
                               onclick="document.getElementById('driverCars').style.display = 'block';
                                       document.getElementsByClassName('buttonAddCar')[0].parentElement.parentElement.setAttribute('journeyid','${journey.journey.id}')"></i>
                        </c:if>

                    </td>
                </c:if>
                <c:if test="${sessionScope.user.role.role eq 'driver'}">
                    <td style="width: 0px"></td>
                </c:if>
                <c:if test="${sessionScope.user.role.role eq 'driver'}">
                    <td driverid="${sessionScope.user.id}" class="w3-center editRow" style="display:none;">
                        <c:if test="${journey.journey.status.status eq 'on_process' && sessionScope.user.id eq journey.idDriver}">
                            <i class="fa fa-check w3-large w3-text-green"
                               onclick="confirmJourney(${journey.journey.id})"></i>
                        </c:if>

                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- PAGINATION -->
<div class="w3-bar w3-padding w3-center">
    <a href="javascript:" onclick="post('/Base/journeys/filter/page1')" class="w3-button">«</a>
    <c:if test="${sessionScope.filterJourney.countPages > 6}">
        <c:if test="${sessionScope.filterJourney.page >= (sessionScope.filterJourney.countPages - 3)}">
            <a href="javascript:" class="w3-button">...</a>
            <c:forEach begin="${sessionScope.filterJourney.countPages - 4}" end="${sessionScope.filterJourney.countPages}" var="i">
                <a href="javascript:" onclick="post('/Base/journeys/filter/page'.concat(${i}))" class="btn-pagination w3-button<c:if test="${sessionScope.filterJourney.page == i}"> w3-red</c:if>">${i}</a>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.filterJourney.page < (sessionScope.filterJourney.countPages - 3)}">
            <c:if test="${sessionScope.filterJourney.page == 1}">
                <c:forEach begin="${sessionScope.filterJourney.page}" end="${sessionScope.filterJourney.page + 3}" var="i">
                    <a href="javascript:" onclick="post('/Base/journeys/filter/page'.concat(${i}))" class="btn-pagination w3-button<c:if test="${sessionScope.filterJourney.page == i}"> w3-red</c:if>">${i}</a>
                </c:forEach>
            </c:if>
            <c:if test="${sessionScope.filterJourney.page != 1}">
                <c:forEach begin="${sessionScope.filterJourney.page - 1}" end="${sessionScope.filterJourney.page + 2}" var="i">
                    <a href="javascript:" onclick="post('/Base/journeys/filter/page'.concat(${i}))" class="btn-pagination w3-button<c:if test="${sessionScope.filterJourney.page == i}"> w3-red</c:if>">${i}</a>
                </c:forEach>
            </c:if>

            <a href="javascript:" class="w3-button">...</a>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.filterJourney.countPages < 6}">
        <c:forEach begin="1" end="${sessionScope.filterJourney.countPages}" var="i">
            <a href="javascript:" onclick="post('/Base/journeys/filter/page'.concat(${i}))" class="btn-pagination w3-button<c:if test="${sessionScope.filterJourney.page == i}"> w3-red</c:if>">${i}</a>
        </c:forEach>
    </c:if>
    <a href="javascript:" onclick="post('/Base/journeys/filter/page'.concat(${sessionScope.filterJourney.countPages}))" class="w3-button">»</a>
</div>
<!-- MODAL MAP -->
<div id="map" class="w3-modal">
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal">&times;</span>
            <h2>Route</h2>
        </header>
        <div class="w3-container w3-modal-content">
            <iframe id="map_iframe" src="#" height="450" frameborder="0"
                    style="border:0; width: inherit; margin-left: -16px" allowfullscreen></iframe>
        </div>
    </div>
</div>

<!-- MODAL ADD NEW ROUTE -->
<div id="addNewRoute" class="w3-modal">
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModalAdd">&times;</span>
            <h2>Route</h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" action="${pageContext.request.contextPath}/journey" method="post">
                <div class="w3-section">
                    <label>Pointy of departure</label>
                    <input name="add" value="s" hidden>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Address or coordinate"
                           id="new_from" name="from" required>
                    <label>Destination</label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Address or coordinate"
                           id="new_where" name="where" required>
                    <label>Weight</label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Weight in kg"
                           id="new_weight" name="weight" required>
                    <label>Volume</label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Volume in m^3"
                           id="new_volume" name="volume" required>
                    <label>Price</label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Price"
                           id="new_price" name="price" required>
                    <label>Type Material</label>
                    <select class="w3-select" name="material" required>
                        <option value="" disabled selected>Choose material</option>
                        <option value="liquid">Liquid</option>
                        <option value="bulk">Bulk</option>
                        <option value="animal">Animal</option>
                        <option value="solid">Container</option>
                        <option value="car">Car</option>
                    </select>
                    <button class="w3-black w3-btn-block w3-section w3-padding">Add</button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:if test="${sessionScope.user.role.role eq 'driver'}">
    <div id="driverCars" class="w3-modal">
        <div class="w3-modal-content">
            <header class="w3-container w3-red">
                <span class="w3-closebtn" id="closeDriverCars"
                      onclick="this.parentElement.parentElement.parentElement.style.display='none'">&times;</span>
                <h2>Driver Cars</h2>
            </header>
            <div class="w3-container w3-modal-content" journeyid="">
                <c:forEach items="${sessionScope.driverCars}" var="car">
                    <h3>${car.mark} ${car.model} <i class="fa fa-share buttonAddCar" carid="${car.id}"></i></h3>
                    <table class="w3-table">
                        <tr class="w3-threequarter">
                            <td>Number</td>
                            <td>${car.number}</td>
                        </tr>
                        <tr class="w3-threequarter">
                            <td>Capacity</td>
                            <td>${car.maxWeight}</td>
                        </tr>
                        <tr class="w3-threequarter">
                            <td>Volume</td>
                            <td>${car.maxVolume}</td>
                        </tr>
                        <tr class="w3-threequarter">
                            <td>Bodywork</td>
                            <td>${car.bodywork.bodywork}</td>
                        </tr>
                        <tr class="w3-threequarter">
                            <td>Status</td>
                            <td>${car.status.status}</td>
                        </tr>
                    </table>
                </c:forEach>

            </div>
        </div>
    </div>
</c:if>

<c:if test="${sessionScope.user.role.role ne 'driver'}">
    <div id="journeyCars" class="w3-modal">
        <div class="w3-modal-content">
            <header class="w3-container w3-red">
                <span class="w3-closebtn" id="closeJourneyCars"
                      onclick="this.parentElement.parentElement.parentElement.style.display='none'">&times;</span>
                <h2>Journey Cars</h2>
            </header>
            <div class="w3-container w3-modal-content" journeyid="">
                <div id="dynamicTable"></div>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.errorCarJourney != null}">
    <div id="driverCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
            <c:if test="${requestScope.errorCarJourney != ''}">
                <div class="w3-panel w3-red w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-red w3-large w3-display-topright">&times;</span>
                    <h3>Danger!</h3>
                    <p>${requestScope.errorCarJourney}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.errorCarJourney == ''}">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Car added to journey</p>
                </div>
            </c:if>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.errorAcceptCarJourney != null}">
    <div id="acceptCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
            <c:if test="${requestScope.errorAcceptCarJourney != ''}">
                <div class="w3-panel w3-red w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-red w3-large w3-display-topright">&times;</span>
                    <h3>Danger!</h3>
                    <p>${requestScope.errorAcceptCarJourney}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.errorAcceptCarJourney == ''}">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Car accepted to journey</p>
                </div>
            </c:if>
        </div>
    </div>
</c:if>

<%@include file="/WEB-INF/views/portfolio.jspf" %>

<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
<script src="${pageContext.request.contextPath}/scripts/journey.js"></script>

<%@include file="/WEB-INF/views/footer.jspf" %>
</body>
</html>
