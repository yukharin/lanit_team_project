<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Notification CRUD Application</title>
    <%--<link rel="stylesheet" href="/css/common.css" type="text/css" />--%>
    <style type="text/css">
        <%@ include file="/css/common.css" %>
    </style>
</head>
<body>

<%--COMMON TEMPLATE was EDITED--%>
<div id="common_header_user">
    <table id="header_table">
        <th style="text-align: left;">
            <h5>ВХОД В ЛИЧНЫЙ КАБИНЕТ ЗАКАЗЧИКА</h5>
        </th>
        <th style="text-align: center;">
            <%--<h5>Oрганизация: ${user.organization.name} </h5>--%>
        </th>
        <th style="text-align: right;">
            <span id="attention_in_header_table">
                <c:if test="${user == null}">
                    (ОБЯЗАТЕЛЬНО ВЫБЕРИТЕ ПОЛЬЗОВАТЕЛЯ !!!)
                </c:if>
                <%--<c:if test="${user.organization.government}">--%>
                    <%--Cотрудник органа власти--%>
                <%--</c:if>--%>
            </span>

            <form action="selectUser" method="POST">
                Текущий Пользователь:
                <select type="text" name="idSelectUser" <%--onchange="this.form.submit()"--%> ><%--multiple="true"--%>
                    <c:forEach items="${user_list}" var="tempUser">
                        <%--<c:choose>--%>
                            <%--<c:when test="${user.id.equals(tempUser.id)}">--%>
                                <%--<option selected--%>
                                        <%--value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <option value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    </c:forEach>
                </select>
                <input type="submit" value="Войти на страницу" class="save" />

                <Br><Br>
                <a class="href-nav-item"
                   href="${pageContext.request.contextPath}/crud/user/list"> Настройка пользователей </a>
                <Br>
            </form>
        </th>
    </table>
</div>

</body>
</html>