<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="onlineList" required="true" %>
<%@ attribute name="user" required="true" %>

<c:if test="${onlineList.contains(user)}">
    <span class="w3-tag w3-green">Online</span>
</c:if>
<c:if test="${!onlineList.contains(user)}">
    <span class="w3-tag w3-deep-orange">Offline</span>
</c:if>