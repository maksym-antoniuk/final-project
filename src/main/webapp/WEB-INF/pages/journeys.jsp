<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 22.01.18
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>JOURNEYS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/${sessionScope.theme}.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
<ma:nav/>
<c:if test="${sessionScope.user != null}">
<h2><fmt:message key="nav.journey"/>
    <div class="w3-dropdown-hover w3-hover-none w3-hover-text-red w3-white">
        <i class="fa fa-gear"><i class="fa fa-caret-down w3-large" style="margin-left: -2px;"></i></i>
        <div id="editPanel" class="w3-dropdown-content w3-bar-block w3-border">
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <h6><i onclick="openModalAdd();" class="fa fa-plus w3-text-light-green w3-large"></i> <fmt:message key="jour.add"/> </h6>
            </c:if>
            <h6><i id="editJourney" class="fa fa-edit w3-text-dark-gray w3-large" style="margin-top: -10px"></i> <fmt:message key="jour.editpanel"/> </h6>
            <h6><fmt:message key="jour.rows"/>: <i id="sendCountRows" class="fa fa-play w3-xlarge w3-text-blue w3-right">  </i><input id="countRows" class="w3-border w3-input w3-half w3-right" type="number" style="margin-top: -10px"></h6>
            <h6><input id="sort_user" class="w3-left" type="checkbox" <c:if test="${sessionScope.filterJourney.onlyMy == true}">checked</c:if>><label><fmt:message key="jour.onlymy"/> </label></h6>
        </div>
    </div>
</h2>

<div class="w3-responsive">
    <table class="w3-table-all">
        <tr class="w3-red">
            <th class="w3-center"><fmt:message key="col.id"/> <i id="sort_id"
                                        class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascId == true}">fa fa-sort-numeric-asc</c:if> <c:if test="${sessionScope.filterJourney.ascId == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascId == false}">fa fa-sort-numeric-desc</c:if>"></i>
            </th>
            <th class="w3-center"><fmt:message key="col.dateStart"/> <i id="sort_date"
                                          class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascDateStart == true}">fa fa-sort-amount-desc</c:if> <c:if test="${sessionScope.filterJourney.ascDateStart == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascDateStart == false}">fa fa-sort-amount-asc</c:if>"></i>
            </th>
            <th class="w3-center"><fmt:message key="col.dateFinish"/> <i id="sort_dateFinish"
                                          class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascDateFinish == true}">fa fa-sort-amount-desc</c:if> <c:if test="${sessionScope.filterJourney.ascDateFinish == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascDateFinish == false}">fa fa-sort-amount-asc</c:if>"></i>
            </th>
            <th class="w3-center"><fmt:message key="col.status"/> <i
                    class="fa fa-filter w3-xlarge w3-text-white w3-hover-text-black w3-dropdown-hover"
                    style="background: none">
                <div class="w3-dropdown-content">
                    <h4><input id="sort_new" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'new'}"> checked</c:if></c:forEach> type="checkbox"><label><fmt:message key="status.new"/> </label></h4>
                    <h4><input id="sort_perform" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'on_process'}"> checked</c:if> </c:forEach>type="checkbox"><label><fmt:message key="status.perform"/></label></h4>
                    <h4><input id="sort_old" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'old'}"> checked</c:if> </c:forEach>type="checkbox"><label><fmt:message key="status.old"/></label></h4>
                    <h4><input id="sort_cancel" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.statusJourneys}" var="status">
                    <c:if test="${status.status eq 'canceled'}"> checked </c:if></c:forEach>type="checkbox"><label><fmt:message key="status.cancel"/></label></h4>
                </div>
            </i>
            </th>
            <th class="w3-center"><fmt:message key="col.price"/> <i id="sort_price"
                                           class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascPrice == true}">fa fa-sort-amount-asc</c:if> <c:if test="${sessionScope.filterJourney.ascPrice == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascPrice == false}">fa fa-sort-amount-desc</c:if>"></i></th>
            <th class="w3-center"><fmt:message key="col.weight"/> <i id="sort_capacity"
                                            class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascCapacity == true}">fa fa-sort-amount-asc</c:if> <c:if test="${sessionScope.filterJourney.ascCapacity == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascCapacity == false}">fa fa-sort-amount-desc</c:if>"></i></th>
            <th class="w3-center"><fmt:message key="col.material"/> <i
                    class="fa fa-filter w3-xlarge w3-text-white w3-hover-text-black w3-dropdown-hover"
                    style="background: none">
                <div class="w3-dropdown-content">
                    <h4><input id="sort_bulk" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'bulk'}"> checked</c:if></c:forEach> type="checkbox"><label><fmt:message key="bulk"/></label></h4>
                    <h4><input id="sort_solid" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'solid'}"> checked</c:if> </c:forEach>type="checkbox"><label><fmt:message key="solid"/></label></h4>
                    <h4><input id="sort_liquid" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'liquid'}"> checked</c:if> </c:forEach>type="checkbox"><label><fmt:message key="liquid"/></label></h4>
                    <h4><input id="sort_car" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'car'}"> checked </c:if></c:forEach>type="checkbox"><label><fmt:message key="car"/></label></h4>
                    <h4><input id="sort_animal" class="w3-left"
                    <c:forEach items="${sessionScope.filterJourney.materials}" var="material">
                    <c:if test="${material.material eq 'animal'}"> checked </c:if></c:forEach>type="checkbox"><label><fmt:message key="animal"/></label></h4>
                </div>
            </i></th>
            <th class="w3-center"><fmt:message key="col.volume"/> <i id="sort_volume"
                                            class="w3-hover-text-black w3-xlarge <c:if test="${sessionScope.filterJourney.ascVolume == true}">fa fa-sort-amount-asc</c:if> <c:if test="${sessionScope.filterJourney.ascVolume == null}">fa fa-sort</c:if><c:if test="${sessionScope.filterJourney.ascVolume == false}">fa fa-sort-amount-desc</c:if>"></i></th>
            <th class="w3-center"><fmt:message key="col.map"/></th>
            <c:if test="${sessionScope.user.role.role eq 'admin'}">
                <th class="w3-center editRow" style="display:none;"><fmt:message key="manager"/></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <th style="width: 0px"></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'admin' || sessionScope.user.role.role eq 'manager'}">
                <th class="w3-center editRow" style="display:none;"><fmt:message key="col.car"/></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <th style="width: 0px"></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role ne 'driver'}">
                <th class="w3-center editRow" style="display:none;"><fmt:message key="col.cancel"/></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'driver'}">
                <th class="w3-center editRow" style="display:none;"><fmt:message key="col.subscribe"/></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'driver'}">
                <th style="width: 0px"></th>
            </c:if>
            <c:if test="${sessionScope.user.role.role eq 'driver'}">
                <th class="w3-center editRow" style="display:none;"><fmt:message key="col.confirm"/></th>
            </c:if>
        </tr>
        <c:forEach items="${requestScope.journeys}" var="journey">
            <tr>
                <td class="w3-center">${journey.journey.id}</td>
                <td class="w3-center">${journey.journey.date}</td>
                <td class="w3-center">${journey.journey.dateFinish}</td>
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
                        <c:if test="${sessionScope.user.role.role eq 'admin' || sessionScope.user.role.role eq 'manager' && journey.journey.idManager eq sessionScope.user.id}">
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
                            <c:if test="${sessionScope.user.role.role eq 'admin' || sessionScope.user.role.role eq 'manager' && sessionScope.user.id eq journey.journey.idManager}">
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
                                       document.getElementsByClassName('buttonAddCar')[0].parentElement.parentElement.parentElement.setAttribute('journeyid','${journey.journey.id}')"></i>
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
            <h2><fmt:message key="col.map"/></h2>
        </header>
        <div class="w3-container w3-modal-content">
            <iframe id="map_iframe" src="#" height="450" frameborder="0"
                    style="border:0; width: inherit; margin-left: -16px" allowfullscreen></iframe>
        </div>
    </div>
</div>

    <c:if test="${requestScope.errorCreateJourney != null && requestScope.createJourneyDTO == null}">
        <div class="w3-modal" style="display: block">
            <div class="w3-modal-content w3-animate-zoom">
                <div class="w3-panel <c:if test="${requestScope.errorCreateJourney.get('Good!') != null}">w3-green</c:if><c:if test="${requestScope.errorCreateJourney.get('Danger!') != null}">w3-redd</c:if>  w3-display-container">
                <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                      class="w3-button <c:if test="${requestScope.errorCreateJourney.get('Good!') != null}">w3-green</c:if><c:if test="${requestScope.errorCreateJourney.get('Danger!') != null}">w3-redd</c:if> w3-large w3-display-topright">&times;</span>
                    <c:forEach var="error" items="${errorCreateJourney}">
                        <h3>${error.key}</h3>
                        <p>${error.value}</p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>

<!-- MODAL ADD NEW ROUTE -->
<div id="addNewRoute" class="w3-modal" <c:if test="${requestScope.createJourneyDTO != null}">style="display: block" </c:if>>
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModalAdd">&times;</span>
            <h2><fmt:message key="jour.jour"/></h2>
        </header>
        <c:if test="${errorCreateJourney != null}">
            <div class="w3-panel w3-redd w3-display-container">
                <span onclick="this.parentElement.style.display='none'"
                      class="w3-button w3-redd w3-large w3-display-topright">&times;</span>
                <c:forEach var="error" items="${errorCreateJourney}">
                    <h3>${error.key}</h3>
                    <p>${error.value}</p>
                </c:forEach>
            </div>
        </c:if>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" action="${pageContext.request.contextPath}/journey" method="post">
                <div class="w3-section">
                    <label><fmt:message key="jour.point"/> </label>
                    <input name="add" value="s" hidden>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="address.placeholder"/>"
                           id="new_from" name="from" value="${createJourneyDTO.from}" required>
                    <label><fmt:message key="jour.dep"/> </label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="address.placeholder"/>"
                           id="new_where" name="where" value="${createJourneyDTO.where}" required>
                    <label><fmt:message key="col.weight"/> </label>
                    <input pattern="[0-9]+|([0-9]\.[0-9]+)" minlength="1" maxlength="11" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="jour.weight.placeholder"/>"
                           id="new_weight" name="weight" value="${createJourneyDTO.weight}" required>
                    <label><fmt:message key="col.volume"/> </label>
                    <input pattern="[0-9]+|([0-9]\.[0-9]+)" minlength="1" maxlength="11" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="jour.volume.placeholder"/>"
                           id="new_volume" name="volume" value="${createJourneyDTO.volume}" required>
                    <label><fmt:message key="col.price"/> </label>
                    <input pattern="[0-9]+|([0-9]\.[0-9]+)" minlength="1" maxlength="11" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="col.price"/>"
                           id="new_price" name="price" value="${createJourneyDTO.price}" required>
                    <label><fmt:message key="col.material"/></label>
                    <select class="w3-select" name="material" required>
                        <option value="" disabled selected><fmt:message key="material.placeholder"/></option>
                        <option value="liquid"><fmt:message key="liquid"/></option>
                        <option value="bulk"><fmt:message key="bulk"/></option>
                        <option value="animal"><fmt:message key="animal"/></option>
                        <option value="solid"><fmt:message key="solid"/></option>
                        <option value="car"><fmt:message key="car"/></option>
                    </select>
                    <button class="w3-black w3-btn-block w3-section w3-padding"><fmt:message key="input.submit"/></button>
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
            <div class="w3-container w3-modal-content" id="addCarjourneyModal" journeyid="">
                <c:forEach items="${sessionScope.driverCars}" var="car">
                    <div class="w3-col l3 m6 w3-margin-bottom w3-row-padding w3-red car-card" style="width: 300px; margin-left: 6px; margin-right: 6px;">
                        <div class="w3-display-container w3-red" style="margin-left: -8px" onmouseover="">
                            <img src="${pageContext.request.contextPath}/resources/image/car/${car.id}.jpg" alt="Fjords" onerror="this.src = '${pageContext.request.contextPath}/img/defaultcar.jpg'"
                            <c:if test="${car.status.status eq 'broken'}">
                                 class="w3-grayscale-max"
                            </c:if>  style="object-fit: cover;width:300px; height: 300px;">
                            <div class="w3-display-topleft w3-black w3-padding" >${car.mark} ${car.model}</div>
                            <c:if test="${car.status.status eq 'broken'}">
                                <div class="w3-display-middle w3-border-red w3-text-red w3-padding w3-text-shadow" style=" border:4px solid"><h1><strong>BROKEN</strong></h1></div>
                            </c:if>
                            <i class="fa fa-share buttonAddCar w3-xxlarge w3-display-topright w3-text-black w3-hover-text-red" carid="${car.id}"></i>
                        </div>
                        <div  style="margin-top: 20px" class="w3-text-white">
                            <p style="margin-top: -15px"><b><fmt:message key="label.car.capacity"/>:</b> ${car.maxWeight} <b><fmt:message key="label.car.volume"/>:</b> ${car.maxVolume}</p>
                            <p style="margin-top: -15px"><b><fmt:message key="label.type.bodywork"/>:</b> ${car.bodywork.bodywork}</p>
                        </div>
                    </div>
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
</c:if>
</body>
</html>
