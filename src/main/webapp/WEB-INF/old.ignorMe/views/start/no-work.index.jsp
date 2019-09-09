<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Notification CRUD Application</title>
	<%--<base href="/">--%>
	<%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
	<%--<link rel="icon" type="image/x-icon" href="src/main/webapp/WEB-INF/ng/favicon.ico">--%>
</head>
<body>

<app-root>загрузка...</app-root>

<Br><Br>
<a class="href-nav-item"
   href="${pageContext.request.contextPath}/output"> Выйти </a>
<hr>
<footer>
	<p>&copy; com.lanit.satonin18 2019</p>
</footer>

<spring:url value="/resources/ng/polyfills-es5.js" var="polyfills-es5Js" />
<spring:url value="/resources/ng/polyfills-es2015.js" var="polyfills-es2015Js" />
<spring:url value="/resources/ng/styles-es2015.js" var="styles-es2015Js" />
<spring:url value="/resources/ng/polyfills-es5.js" var="styles-es5Js" />
<spring:url value="/resources/ng/polyfills-es5.js" var="runtime-es2015Js" />
<spring:url value="/resources/ng/polyfills-es5.js" var="vendor-es2015Js" />
<spring:url value="/resources/ng/polyfills-es5.js" var="main-es2015Js" />
<spring:url value="/resources/ng/polyfills-es5.js" var="runtime-es5Js" />
<spring:url value="/resources/ng/polyfills-es5.js" var="vendor-es5Js" />
<spring:url value="/resources/ng/polyfills-es5.js" var="main-es5Js" />

<script src="${polyfills-es5Js}" nomodule defer></script>
<script src="${polyfills-es2015Js}" type="module"></script>
<script src="${styles-es2015Js}" type="module"></script>
<script src="${styles-es5Js}" nomodule defer></script>
<script src="${runtime-es2015Js}" type="module"></script>
<script src="${vendor-es2015Js}" type="module"></script>
<script src="${main-es2015Js}" type="module"></script>
<script src="${runtime-es5Js}" nomodule defer></script>
<script src="${vendor-es5Js}" nomodule defer></script>
<script src="${main-es5Js}" nomodule defer></script>

</body>
</html>