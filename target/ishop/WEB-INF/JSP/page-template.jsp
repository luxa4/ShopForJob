<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ishop - main page</title>


	<link href="<c:url value="/static/css/bootstrap-theme.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/font-awesome.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/app.css" />" rel="stylesheet">



</head>
<body>
	<header>
		<jsp:include page="fragment/header.jsp" />
	</header>
	<div class="container-fluid">
		<div class="row">
			<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<jsp:include page="fragment/aside.jsp" />
			</aside>
			<main class="col-xs-12 col-sm-8 col-md-9 col-lg-10"> 
				<jsp:include page="${CURRENT_PAGE}" />
			</main>
		</div>
	</div>
	<footer class="footer">
		<jsp:include page="fragment/footer.jsp" />
	</footer>


    <script src="<c:url value="/static/js/jquery.js"/>" ></script>
    <script src="<c:url value="/static/js/bootstrap.js"/>" ></script>
    <script src="<c:url value="/static/js/app.js"/>" ></script>
</body>
</html>