<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>saveOrUpdate User</title>
    <!--link type="text/css" rel="stylesheet" href="@{pageContext.request.contextPath}/resources/css/style.css" />
    <link type="text/css" rel="stylesheet" href="@{pageContext.request.contextPath}/resources/css/form-add-style.css" /-->
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>User CRUD Application</h2>
    </div>
</div>

<div id="container">
    <h2>SaveOrUpdate User</h2>

    <form action="apply_update" method="post" accept-charset="UTF-8" ><%--modelAttribute="user"--%>

        <%--<form:hidden path="id" />--%>
        <input type="hidden" name="id" value="${user.id}">

        <table>
            <tr>
                <td><label>First name: </label></td>
                <td><input type="text" name="firstName" value="${user.firstName}"></input></td>
            </tr>
            <tr>
                <td><label>Last name: </label></td>
                <td><input type="text" name="lastName" value="${user.lastName}"></input></td>
            </tr>
            <tr>
                <td><label>Organization: </label></td>
                <td>
                    <%--<pre>--%>
                        <select  type="text" name="idOrg" ><%--multiple="true"--%>
                            <option selected value ="${user.organization.id}">(заданное)${user.organization.name}</option>
                            <%--<form:options items="${listOrg}"  itemLabel="name" itemValue="id" />--%>
                            <c:forEach items="${listOrg}" var="org">
                                <option value ="${org.id}">${org.name}</option>
                            </c:forEach>
                        </select>
                    <%--</pre>--%>

                </td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>
        </table>
    </form>



    <p>
        <a href="${pageContext.request.contextPath}/crud/user/list"> Back to list </a>
    </p>

</div>
</body>
</html>