<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="../mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 03.02.18
  Time: 21:37
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
<div>
    <img src="${pageContext.request.contextPath}/resources/image/user/${sessionScope.user.id}.jpg" alt="Fjords" onerror="this.src = '${pageContext.request.contextPath}/img/L2.png'"
         style="object-fit: cover;width:300px; height: 300px;">
    <i class="fa fa-gear w3-xxlarge w3-right w3-text-black w3-hover-text-red w3-dropdown-hover">
        <div class="w3-dropdown-content" style="min-width: auto; background: none;">
            <h4><i class="fa fa-edit w3-xxlarge w3-hover-text-white"></i></h4>
            <h4><i class="fa fa-camera-retro w3-xxlarge w3-hover-text-red" onclick="document.getElementById('uploadModal').style.display = 'block';"></i></h4>
        </div>
    </i>
</div>
<div class="w3-container">
    <table class="w3-table">
        <tr><th>${requestScope.portfolio.username}</th></tr>
        <tr><th>${requestScope.portfolio.surname}</th></tr>
        <tr><th>${requestScope.portfolio.email}</th></tr>
        <tr><th>${requestScope.portfolio.phone}</th></tr>
        <tr><th>${requestScope.portfolio.days}</th></tr>
        <tr><th>${requestScope.portfolio.countJourney}</th></tr>
    </table>
</div>

<!-- MODAL UPLOAD -->
<div id="uploadModal" class="w3-modal">
    <div class="w3-modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal" onclick="this.parentElement.parentElement.parentElement.style.display = 'none'">&times;</span>
            <h2>Upload Image</h2>
        </header>
        <div class="w3-container w3-modal-content">
            <form class="w3-container" id="formUp" method="post" action="${pageContext.request.contextPath}/resources/image/user/${sessionScope.user.id}" enctype="multipart/form-data">
                <div class="w3-section">
                    <input name="file" id="file-5" class="inputfile inputfile-4" style="opacity: 0" data-multiple-caption="{count} files selected" multiple="" type="file" accept="image/* ">
                    <label for="file-5"><i class="fa fa-upload w3-xxxlarge w3-row"></i> <span>Choose file...</span></label>
                    <div id="uic" name="uploadImgCar" value="5">
                        <input class="w3-quarter" style="opacity: 0" >
                        <button class="w3-black w3-btn-block w3-section w3-padding w3-half w3-center">Add</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jspf" %>
</body>
</html>
