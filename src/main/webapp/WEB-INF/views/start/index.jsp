<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
	<title>LKZ</title>

	<spring:url value="/resources/core/css/hello.css" var="coreCss" />
	<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
	<spring:url value="/resources/core/css/open_layers.87e34a8b.css" var="open_layersCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
    <style>#map{width:400px;height:250px}</style>
    <link href="${open_layersCss}" rel="stylesheet" />
</head>
</body>

<div id="content">
	<h1>Welcome</h1>
	<app-root>Загрузка...</app-root>
	<div id="map"></div>
</div>
<hr>
<footer>
	<p>&copy; com.lanit.satonin18 2019</p>
</footer>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/core/js/open_layers.e0ff072e.js" var="open_layersJs" />
<spring:url value="/resources/core/js/polyfills.js" var="polyfillsJs" />
<spring:url value="/resources/core/js/ng_app.js" var="ng_appJs" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${bootstrapJs}"></script>
<script src="${coreJs}"></script>
<script src="${open_layersJs}"></script>
<script src="${polyfillsJs}"></script>
<script src="${ng_appJs}"></script>

</body>
</html>