<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--info line--%>
<div class="alert alert-info">
    <p>Found <strong>${productCount}</strong></p>
</div>

<jsp:include page="products.jsp" />