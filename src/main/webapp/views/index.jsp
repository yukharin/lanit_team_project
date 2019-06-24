<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Index Page</title>
</head>

<body>
<spring:form method="post"  modelAttribute="organizationJSP" action="check-organization">

    Name: <spring:input path="name"/> (path="" - указывает путь, используемый в modelAttribute=''. в нашем случае organization.name)  <br/>
    orgType: <spring:input path="government"/>   <br/>
    <spring:button>Next Page</spring:button>

</spring:form>

</body>
</html>