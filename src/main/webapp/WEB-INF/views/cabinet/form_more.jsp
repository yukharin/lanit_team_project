<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Notification CRUD Application</title>
    <style type="text/css">
        div{
            margin: 9px 8px;
        }

        TABLE {
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
            width: 100%;/*  width: 300px; Ширина таблицы */
        }
        TH, TD {
            border: 1px solid black; /* Параметры рамки */
            text-align: center; /* Выравнивание по центру */
            padding: 1px; /* Поля вокруг текста */
        }
        TH {
            background: #fc0; /* Цвет фона ячейки */
            height: 40px; /* Высота ячеек */
            /*vertical-align: bottom;  Выравнивание по нижнему краю */
            padding: 0; /* Убираем поля вокруг текста */
        }
        #header_table th{
            background: #ffccff;
        }
        #attention_in_header_table{
            color: blue;
        }

        .nav-item {
            border:1px solid #ccc;  /*рамка*/
            background:#eaeaea; /*фон*/
            padding: .2em .2em; /*отступы внутри*/
            text-decoration: none; /*убрать подчеркивание ссылки*/
        }
        #nav-item-current {
            background:#ff0000; /*фон*/
            color: white;
        }
    </style>
</head>
<body>

<%--TODO: NEED HAVE COMMON TEMPLATE--%>
<div id="common_header_user">
    <table id="header_table">
        <th style="text-align: left;">
            <h5>ЛИЧНЫЙ КАБИНЕТ ЗАКАЗЧИКА</h5>
        </th>
        <th style="text-align: center;">
            <h5>Oрганизация: ${user.organization.name} </h5>
        </th>
        <th style="text-align: right;">
            <p id="attention_in_header_table">
                <c:if test="${user == null}">
                    (ОБЯЗАТЕЛЬНО ВЫБЕРИТЕ ПОЛЬЗОВАТЕЛЯ !!!)
                </c:if>
                <c:if test="${user.organization.government}">
                    Cотрудник органа власти
                </c:if>
            </p>

            <form action="selectUser" method="POST">
                Текущий Пользователь:
                <select type="text" name="idSelectUser" onchange="this.form.submit()" ><%--multiple="true"--%>
                    <c:forEach items="${user_list}" var="tempUser">
                        <c:choose>
                            <c:when test="${user.id.equals(tempUser.id)}">
                                <option selected
                                        value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <%--<input type="submit" value="Войти на страницу" class="save" />--%>
            </form>
        </th>
    </table>
</div>

<div id="content">

    <div id="common_info_about_notification">

    </div>
    <%--DIV id="contract"--%>
    <div id="answer_preparation">

    </div>

    <div id="actions">
        <div id="add_action">

        </div>
        <div id="list_actions">

        </div>
    </div>
</div>

<div id="return">
    <a href="${pageContext.request.contextPath}/cabinet/list"> Back to list </a>
</div>

</body>
</html>