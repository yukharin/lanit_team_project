<%--
  Created by IntelliJ IDEA.
  Organization: saton
  Date: 21.06.2019
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Organization CRUD Application</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Organization CRUD Application</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <input type="button" value="Add Customer"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"
        />

        <!--  add a search box -->
        <form:form action="search" method="POST">
            Search organization: <input type="text" name="theSearchName" />

            <input type="submit" value="Search" class="add-button" />
        </form:form>

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Date</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempOrganization" items="${listOfOrganizations}">

                <c:url var="updateLink" value="showFormForUpdate">
                    <c:param name="organizationId" value="${tempOrganization.id}"/>
                </c:url>

                <c:url var="deleteLink" value="delete">
                    <c:param name="organizationId" value="${tempOrganization.id}"/>
                </c:url>

                <tr>
                    <td>${tempOrganization.firstName}</td>
                    <td>${tempOrganization.lastName}</td>
                    <td>${tempOrganization.email}</td>
                    <td>${tempOrganization.date}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>