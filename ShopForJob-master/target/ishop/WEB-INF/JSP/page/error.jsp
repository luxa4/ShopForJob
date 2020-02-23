<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--On this page we show:
        1 - Code from server
        2 - Message about exception
--%>

<div class="alert alert-danger">
    <h1>Code: ${statusCode}</h1>

<c:choose>
    <c:when test="${statusCode == 403}" > You don't have permission to visit this page.</c:when>
    <c:when test="${statusCode == 404}" > This page doesn't exist.</c:when>
    <c:otherwise> Can't process this request. Please, try again later.</c:otherwise>
</c:choose>
</div>