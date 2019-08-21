<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <title>Вход АСКД ЛКЗ</title>
    <%--<link rel="stylesheet" href="/css/common.css" type="text/css" />--%>
    <style type="text/css">
        <%--<%@ include file="/css/common.css" %>--%>
        .error {
            color: red;
        }
        .success {
            color: green;
        }
    </style>
</head>
<body>

<h1 style="text-align: center;">Вход АСКД ЛКЗ</h1>
<h4 style="text-align: center;">Login Form</h4>

<form action='<spring:url value="/loginAction"/>' method="post">
    <c:if test="${error != null}" >
        <div class="error" style="text-align: center;">
            Invalid username and password.
        </div>
    </c:if>

    <c:if test="${messageRegistrationSuccess != null}" >
        <div class="success" style="text-align: center;">
            Регистрация прошла успешна
        </div>
    </c:if>

    <table style="margin: auto; width: auto;">
        <tr>
            <td>Username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>
                <input type="checkbox"
                        name="remember" value="true">
            </td>
            <td><label>Запомнить меня?</label></td>
        </tr>
        <tr>
            <td >
                <button type="submit">Login</button>
            </td>
        </tr>
    </table>
    <Br>
    <table style="margin: auto; width: auto;">
        <tr>
            <td >
                Вы ещё не зарегестрированы?
                <a href="registration">Регистрация</a>
            </td>
        </tr>
    </table>
</form>
<br/>

</body>
</html>