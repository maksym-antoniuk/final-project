<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 06.02.18
  Time: 4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/mytag.tld" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ma" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>500</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/${sessionScope.theme}.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<section class="w3-container w3-padding-0 w3-redd">
    <h1 class="w3-jumbo" style="font-size: 200px">500: что-то явно пошло не так, но не унывай, порисуй на уточке:)</h1>
    <div class="w3-center" id="canvasDiv"></div>
</section>
<%@include file="/WEB-INF/views/footer.jspf" %>
<!--<script type="text/javascript" src="excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/html5-canvas-drawing-app.js"></script>
<script type="text/javascript">
    drawingApp.init();
</script>
</body>
</html>
