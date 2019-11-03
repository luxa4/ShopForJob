<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ishop - main page</title>


	<%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
<link href="/static/css/app.css" type="text/css" rel="stylesheet">
<link href="/static/css/bootstrap-theme.css" type="text/css" rel="stylesheet">
<link href="/static/css/font-awesome.css" type="text/css" rel="stylesheet">
<link href="/static/css/bootstrap.css" type="text/css" rel="stylesheet">

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
	<script src="/static/js/jquery.js" ></script>
	<script src="/static/js/bootstrap.js" ></script>
	<script src="/static/js/app.js" ></script>
</body>
</html>