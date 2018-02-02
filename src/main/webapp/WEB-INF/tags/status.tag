<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="category" required="true" %>
<c:choose>
    <c:when test="${\"new\" == category}">
        <span class="w3-tag w3-blue">New!</span>
    </c:when>
    <c:when test="${\"on_process\" == category}">
        <span class="w3-tag w3-teal">Perform</span>
    </c:when>
    <c:when test="${\"old\" == category}">
        <span class="w3-tag w3-orange">Old</span>
    </c:when>
    <c:when test="${\"canceled\" == category}">
        <span class="w3-tag w3-red">Canceled</span>
    </c:when>
</c:choose>