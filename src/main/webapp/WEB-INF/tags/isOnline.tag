<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="onlineList" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<%@ attribute name="user" required="true" %>

<c:if test="${onlineList.contains(user)}">
    <span class="w3-tag w3-green"><fmt:message key="online"/></span>
</c:if>
<c:if test="${!onlineList.contains(user)}">
    <span class="w3-tag w3-deep-orange"><fmt:message key="offline"/></span>
</c:if>