<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
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

    <form:form action="saveOrUpdate" modelAttribute="user" method="post">

        <form:hidden path="id" />

        <table>
            <tr>
                <td><label>First name: </label></td>
                <td><form:input path="firstName"></form:input></td>
            </tr>
            <tr>
                <td><label>Last name: </label></td>
                <td><form:input path="lastName"></form:input></td>
            </tr>
            <tr>
                <td><label>Organization: </label></td>
                <td>
                    <%--<pre>--%>
                        <form:select  path="userOrg" ><%--multiple="true"--%>
                            <form:options items="${listOrg}"  itemLabel="name" itemValue="id" />
                            <%--<c:forEach items="${listOrg}" var="org">--%>
                                <%--<form:option value ="${org.id}" label="${org.toString()}"></form:option>--%>
                            <%--</c:forEach>--%>
                        </form:select>
                    <%--</pre>--%>

                </td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>
        </table>
    </form:form>



    <p>
        <a href="${pageContext.request.contextPath}/user/list"> Back to list </a>
    </p>

</div>
</body>
</html>