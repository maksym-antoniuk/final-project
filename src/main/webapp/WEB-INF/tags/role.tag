<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="role" required="true" %>
<c:choose>
    <c:when test="${\"admin\" == role}">
        <span class="w3-tag w3-red">ADMIN</span>
    </c:when>
    <c:when test="${\"manager\" == role}">
        <span class="w3-tag w3-indigo">MANAGER</span>
    </c:when>
    <c:when test="${\"driver\" == role}">
        <span class="w3-tag w3-lime">DRIVER</span>
    </c:when>
</c:choose>