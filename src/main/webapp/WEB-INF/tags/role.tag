<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<%@ attribute name="role" required="true" %>
<c:choose>
    <c:when test="${\"admin\" == role}">
        <span class="w3-tag w3-red"><fmt:message key="admin"/></span>
    </c:when>
    <c:when test="${\"manager\" == role}">
        <span class="w3-tag w3-indigo"><fmt:message key="manager"/></span>
    </c:when>
    <c:when test="${\"driver\" == role}">
        <span class="w3-tag w3-lime"><fmt:message key="driver"/></span>
    </c:when>
</c:choose>