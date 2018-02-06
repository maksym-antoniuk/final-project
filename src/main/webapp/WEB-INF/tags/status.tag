<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<%@ attribute name="category" required="true" %>
<c:choose>
    <c:when test="${\"new\" == category}">
        <span class="w3-tag w3-blue"><fmt:message key="status.new"/></span>
    </c:when>
    <c:when test="${\"on_process\" == category}">
        <span class="w3-tag w3-teal"><fmt:message key="status.perform"/></span>
    </c:when>
    <c:when test="${\"old\" == category}">
        <span class="w3-tag w3-orange"><fmt:message key="status.old"/></span>
    </c:when>
    <c:when test="${\"canceled\" == category}">
        <span class="w3-tag w3-red"><fmt:message key="status.cancel"/></span>
    </c:when>
</c:choose>