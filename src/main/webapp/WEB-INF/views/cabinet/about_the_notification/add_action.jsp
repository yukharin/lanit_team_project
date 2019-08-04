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
        TABLE.table4input_data {
            width: auto/*  width: 300px; Ширина таблицы */
        }
        TH, TD {
            border: 1px solid black; /* Параметры рамки */
            text-align: center; /* Выравнивание по центру */
            padding: 1px; /* Поля вокруг текста */
        }
        TABLE.table4input_data TH, TD {
            text-align: left; /* Выравнивание по центру */
        }
        TH {
            background: #fc0; /* Цвет фона ячейки */
            height: 40px; /* Высота ячеек */
            /*vertical-align: bottom;  Выравнивание по нижнему краю */
            padding: 0; /* Убираем поля вокруг текста */
        }
        TABLE.table4input_data TH {
            background: #a9ff78; /* Цвет фона ячейки */
        }
        #header_table th{
            background: #ffccff;
        }
        #attention_in_header_table{
            color: blue;
        }

        .href-nav-item {
            background:#eaeaea; /*фон*/

            border:1px solid #ccc;  /*рамка*/
            padding: .2em .2em; /*отступы внутри*/
            text-decoration: none; /*убрать подчеркивание ссылки*/
        }
        .href-nav-item-current {
            background:#ff0000; /*фон*/
            color: white;

            border:1px solid #ccc;  /*рамка*/
            padding: .2em .2em; /*отступы внутри*/
            text-decoration: none; /*убрать подчеркивание ссылки*/
        }

        .green_button{
            background-color: #4CAF50;
            color: white;
            display: inline-block;
        }

        span.radioDecorator > input{ /* HIDE RADIO */
            visibility: hidden; /* Makes input not-clickable */
            position: absolute; /* Remove input from document flow */
        }
        span.radioDecorator > input + button{ /* IMAGE STYLES */
            cursor:pointer;
            border:2px solid transparent;
        }
        span.radioDecorator> input:checked + button{ /* (RADIO CHECKED) IMAGE STYLES */
            border:2px solid #f00;
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
            <span id="attention_in_header_table">
                <c:if test="${user == null}">
                    (ОБЯЗАТЕЛЬНО ВЫБЕРИТЕ ПОЛЬЗОВАТЕЛЯ !!!)
                </c:if>
                <c:if test="${user.organization.government}">
                    Cотрудник органа власти
                </c:if>
            </span>

            <%--<form action="selectUser" method="POST">--%>
            Текущий Пользователь: ${user.firstName} ${user.lastName}
            <%--<select type="text" name="idSelectUser" onchange="this.form.submit()" >&lt;%&ndash;multiple="true"&ndash;%&gt;--%>
            <%--<c:forEach items="${user_list}" var="tempUser">--%>
            <%--<c:choose>--%>
            <%--<c:when test="${user.id.equals(tempUser.id)}">--%>
            <%--<option selected--%>
            <%--value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<option value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>--%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--<input type="submit" value="Войти на страницу" class="save" />--%>

            <%--<Br><Br>--%>
            <%--<a class="href-nav-item"--%>
            <%--href="${pageContext.request.contextPath}/user/list"> Настройка пользователей </a>--%>
            <%--<Br>--%>
            <Br><Br>
            <a class="href-nav-item"
               href="${pageContext.request.contextPath}/output"> Выйти </a>
            <Br>
            <%--</form>--%>
        </th>
    </table>
</div>

<div id="content">

    <div id="add_action">

        <form action="save"  method="get">

            <table class="table4input_data">
                <tr>
                    <th>
                        Ответственный исполнитель заказчика:
                    </th>
                    <td>
                        <select type="text" name="idUserImplementor" <%--onchange="this.form.submit()"--%> ><%--multiple="true"--%>
                            <c:forEach items="${currentNotification.organization.users}" var="tempUser">
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
                    </td>
                </tr>
                <tr>
                    <th>
                        Действие:
                    </th>
                    <td>
                        <select type="text" name="idActionType" <%--onchange="this.form.submit()"--%> ><%--multiple="true"--%>
                            <c:forEach items="${listActionType}" var="tempActionType">
                                <option value ="${tempActionType.id}">${tempActionType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>
                        Статус:
                    </th>
                    <td>
                        <select type="text" name="idNotificationStatus" <%--onchange="this.form.submit()"--%> ><%--multiple="true"--%>
                            <c:forEach items="${listStatus}" var="tempStatus">
                                <c:choose>
                                    <c:when test="${currentNotification.status.id.equals(tempStatus.id)}">
                                        <option selected
                                                value ="${tempStatus.id}">${tempStatus.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value ="${tempStatus.id}">${tempStatus.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>
                        Комментарий:
                    </th>
                    <td>
                                <textarea id="comment" name="content" maxlength="300"
                                          rows="4" cols="50"></textarea>
                    </td>
                </tr>
            </table>

            <Br>
            <button type="button"<%--type="form.submit"--%>
                    style="background-color: #4CAF50; color: white; display: inline-block;" <%--class="green_button"--%>
                    onclick="
                    if(document.getElementById('comment').value == '') {
                        alert('поле - Комментарий пустое');
                    }else {
                        //alert('bag');
                        this.form.submit()
                    }
                    "
            >
                СОХРАНИТЬ
            </button>

            <button type="button"<%--type="form.submit"--%>
                    style="background-color: #4CAF50; color: white; display: inline-block;" <%--class="green_button"--%>
                    onclick="
                            window.location.href='${pageContext.request.contextPath}/cabinet/about_the_notification/actions'
                            "
            >
                ОТМЕНА
            </button>

        </form>
    </div>

</div>

<%--<div id="return">--%>
    <%--<a href="${pageContext.request.contextPath}/cabinet/about_the_notification/actions"> Back to "about the notification_app" </a>--%>
<%--</div>--%>

<script>
    <%--function changeVisibilityById(idDivVisibility, idButton) {--%>
        <%--var divVisibility = document.getElementById(idDivVisibility);--%>
        <%--var button = document.getElementById(idButton);--%>

        <%--divVisibility.style.display = (divVisibility.style.display=='none')? '' : 'none'--%>
        <%--button.innerHTML  = (button.innerHTML == "/\\") ? "\\/" : "/\\";--%>

        <%--button.className = (button.className == "href-nav-item" ? "href-nav-item-current" : "href-nav-item");--%>
    <%--}--%>

    <%--document.getElementById("orderFieldName=${orderFieldNameAction}&desc=${descAction}").classList.add('href-nav-item-current');--%>

    <%--document.getElementById("radio-orderFieldName-orderFieldName=${orderFieldNameAction}&desc=${descAction}").setAttribute('checked', true);--%>
    <%--document.getElementById("radio-desc-orderFieldName=${orderFieldNameAction}&desc=${descAction}").setAttribute('checked', true);--%>

    <%--//todo; trows Excpetion if element=null //JavaScript simple do not run next command line, ПОЭТОМУ this line is last--%>
    <%--document.getElementById("radioPageCount_${paginationAction.currentPage}").setAttribute('checked', true); --%>

</script>
</body>
</html>