<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<c:choose>
    <c:when test="${\"driver\" == sessionScope.user.role.role}">
        <%@include file="../views/driver_nav.jspf" %>
    </c:when>
    <c:when test="${\"manager\" == sessionScope.user.role.role}">
        <%@include file="../views/manager_nav.jspf" %>
    </c:when>
    <c:when test="${\"admin\" == sessionScope.user.role.role}">
        <%@include file="../views/admin_nav.jspf" %>
    </c:when>
</c:choose>