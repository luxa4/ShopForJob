<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" isELIgnored="false" %>
<%@ attribute name="classes" required="false" type="java.lang.String" %>

<%@ taglib prefix="u" uri="/WEB-INF/tags.tld" %>

<form action="/sign-in" method="post" >
    <%--Получаем от Фильтра SetCurrentRequestUrlFilter атрибут CURRENT_REQUEST_URL--%>
    <%--Вставляем атрибут в таг, который его кодирует --%>
    <u:urlEncode var="encodedUrl" url="${CURRENT_REQUEST_URL}" />
        <%--Через скрытый параметр формы передаем атрибут в Controller /sing-in --%>
    <input type="hidden" name="target" value="${encodedUrl}">
    <button type="submit" class="btn btn-primary ${classes}"><i class="fa fa-facebook-official" aria-hidden="true"></i> Sign in</button>
</form>

