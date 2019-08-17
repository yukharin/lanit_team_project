<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <title>Вход АСКД ЛКЗ</title>
</head>
<body>

<h1 style="text-align: center;">Вход АСКД ЛКЗ</h1>
<h4 style="text-align: center;">Login Form</h4>

<%--<form action='<spring:url value="/loginAction"/>' method="post">--%>
<form action='<spring:url value="/loginAction"/>' method="post">
    <table style="margin: auto;">
        <tr>
            <td>Username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td >
                <button type="submit">Login</button>
            </td>
        </tr>
    </table>
</form>
<br/>

</body>
</html>