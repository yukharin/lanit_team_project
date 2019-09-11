<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"--%>
<%--"http://www.w3.org/TR/html4/loose.dtd">--%>

<%--//html//head--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

<div id="common_header_user">
    <table id="header_table">
        <th style="text-align: left;">
            <h5>ЛИЧНЫЙ КАБИНЕТ ЗАКАЗЧИКА</h5>
        </th>
        <th style="text-align: center;">
            <h5>Oрганизация: ${user.organization.name} </h5>
        </th>
        <th style="text-align: right;">
            <span id="attention_in_header_table">
                <c:if test="${user == null}">
                    (ОБЯЗАТЕЛЬНО ВЫБЕРИТЕ ПОЛЬЗОВАТЕЛЯ !!!)
                </c:if>
                <c:if test="${user.organization.government}">
                    Cотрудник органа власти
                </c:if>
            </span>

            Текущий Пользователь: ${user.firstName} ${user.lastName}

            <Br><Br>
            <a class="href-nav-item"
               href="${pageContext.request.contextPath}/output"> Выйти </a>
            <Br>
            <%--</form>--%>
        </th>
    </table>
</div>