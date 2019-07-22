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

        .hidden_input{ /* HIDE RADIO */
            visibility: hidden; /* Makes input not-clickable */
            position: absolute; /* Remove input from document flow */
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
               href="${pageContext.request.contextPath}/"> Выйти </a>
            <Br>
            <%--</form>--%>
        </th>
    </table>
</div>

<div id="content">

    <form action="moreNew"  method="get">

    <div id="common_info_about_notification">
        <h5 style="text-transform: uppercase;">
            ${currentNotification.notificationType}

            <%--TODO ПЕРЕДАТЬ НАЖАТЫЕ КНОПКИ НА СЕРВЕР И ОБРАТНО--%>
            <%--<c:choose>--%>
                <%--<c:when test="${controlled_visibilit.id = 1}">--%>
                    <%--&lt;%&ndash;<form action="editStatus" method="get">&ndash;%&gt;--%>
                    <%--<input checked--%>
                           <%--type="checkbox" class="hidden_input checkbox-controlled_visibility"--%>
                           <%--id="checkbox-controlled_visibility-common_info_about_notification"--%>
                           <%--name="controlled_visibility" value="checked"></input>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--<input type="checkbox" class="hidden_input"--%>
                           <%--id="checkbox-controlled_visibility-common_info_about_notification"--%>
                           <%--name="controlled_visibility" value="checked"></input>--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>

                <button type="button"
                        id = "button-common_info_about_notification" class="href-nav-item-current button-default-visibility-false"
                        onclick="
                    changeVisibilityById('controlled_visibility-common_info_about_notification', 'button-common_info_about_notification')
                    "
                >
                    /\
                </button>
        </h5>
        <div class="controlled_visibility div-default-visibility-false" id="controlled_visibility-common_info_about_notification"
             style="display: none">
            <table class="table4input_data">
                <tr>
                    <th>
                        Номер уведомления:
                    </th>
                    <td>
                        <input type="text" name="id" value="${currentNotification.id}"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>
                        Номер письма:
                    </th>
                    <td>
                        <input type="text" name="letterNumber" value="${currentNotification.letterNumber}"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>
                        Заказчик:
                    </th>
                    <td>
                        <input type="text" name="organization.name" value="${currentNotification.organization.name}"
                               disabled>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <%--DIV id="contract"--%>

    <div id="answer_preparation">
        <h5 style="text-transform: uppercase;"> <%--onclick=""--%>
            ПОДГОТОВКА ОТВЕТА
            <button type="button"
                    id = "button-answer_preparation" class="href-nav-item-current button-default-visibility-false"
                    onclick="changeVisibilityById('controlled_visibility-answer_preparation', 'button-answer_preparation')"
            >
                /\
            </button>
        </h5>
        <div class="controlled_visibility div-default-visibility-false" id="controlled_visibility-answer_preparation"
             style="display: none">
            <table class="table4input_data">
                <tr>
                    <th>
                        Ответ направлен:
                    </th>
                    <td>
                        (не понятна бизнес логика)(Mock)(Индикатор состояния)
                    </td>
                </tr>
                <tr>
                    <th>
                        Срок предоставления ответа:
                    </th>
                    <td>
                        <input type="text" name="dateResponse" value="${currentNotification.dateResponse.toString()}"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>
                        Заказчик:
                    </th>
                    <td>
                        <input type="text" name="organization.name" value="${currentNotification.organization.name}"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>
                        Дата последнего направления ответа:
                    </th>
                    <td>
                        <input type="text" name="MOCK_lastAction_Date" value="${lastAction.date}"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>
                        Дата получения уведомления:
                    </th>
                    <td>
                        <input type="text" name="dateReceived" value="${currentNotification.dateReceived.toString()}"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>
                        Статус уведомления:
                    </th>
                    <td>
                        <input type="text" name="notificationStatus.name" value="${currentNotification.notificationStatus.name}"
                               disabled>
                    </td>
                </tr>
                <tr>
                    <th>
                        Куратор:
                    </th>
                    <td>
                        <input type="text" name="userByIdUserCuratorGos"
                               value="${currentNotification.userByIdUserCuratorGos.firstName.trim()} ${currentNotification.userByIdUserCuratorGos.lastName.trim()}"
                               disabled>
                        (не понятна бизнес логика)(Mock)[Должно быть: Поле для ввода и отправки]
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div id="actions">

        <h5 style="text-transform: uppercase;">
            ДЕЙСТВИЯ
            <button type="button"
                    id = "button-actions" class="href-nav-item"
                    onclick="changeVisibilityById('controlled_visibility-actions', 'button-actions')"
            >
                \/
            </button>
        </h5>
        <div class="controlled_visibility" id="controlled_visibility-actions">
            <div id="add_action">

                <c:url var="addLink" value="addAction/formPage">
                    <c:param name="notificationId" value="${currentNotification.id}"/>
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <%--<a style="background-color: #4CAF50; color: white; display: inline-block;" &lt;%&ndash;class="green_button"&ndash;%&gt;--%>
                   <%--href="${addLink}">Добавить</a>--%>
                <button type="button"  <%--type="form.submit"--%>
                        style="background-color: #4CAF50; color: white; display: inline-block;" <%--class="green_button"--%>
                        onclick="
                                window.location.href='${addLink}'
                                "
                >
                    Добавить
                </button>
            </div>

                <div id="paginationAction">
                    <table>
                        <td style="text-align: left">
                            Найдено записей: ${paginationAction.totalRecords}<Br>
                        </td>
                        <td style="text-align: center">
                            <div id="page-navigator">
                                <c:if test="${paginationAction.totalPages > 1}">

                            <span class ="radioDecorator">
                                <input id="radioPageFirst"
                                       type="radio"
                                       name="page" value="1">
                                <button type="button"  class="href-nav-item"
                                        onclick="
                                        document.getElementById('radioPageFirst').setAttribute('checked', true);
                                        this.form.submit()" >
                                    <<
                                </button>
                            </span>

                                    <span class ="radioDecorator">
                                <input id="radioPagePrev"
                                       type="radio"
                                       name="page" value="${(paginationAction.currentPage != 1) ? paginationAction.currentPage-1 : 1 }">
                                <button type="button"  class="href-nav-item"
                                        onclick="
                                        document.getElementById('radioPagePrev').setAttribute('checked', true);
                                        this.form.submit()">
                                    <-
                                </button>
                            </span>

                                    <%--<a class="href-nav-item"--%>
                                    <%--&lt;%&ndash;href="selectPagination?maxResult=${paginationAction.maxResult}&page=1"> << </a>&ndash;%&gt;--%>
                                    <%--href="listNew?page=1"> << </a>--%>

                                    <%--<a class="href-nav-item"--%>
                                    <%--&lt;%&ndash;href="selectPagination?maxResult=${paginationAction.maxResult}&page=${(paginationAction.currentPage != 1) ? paginationAction.currentPage-1 : 1 }"> <- </a>&ndash;%&gt;--%>
                                    <%--href="listNew?page=${(paginationAction.currentPage != 1) ? paginationAction.currentPage-1 : 1 }"> <- </a>--%>

                                    <c:forEach items="${paginationAction.navigationPages}" var = "page">
                                        <c:choose>
                                            <c:when test="${page != -1 }">

                                                <c:if test="${page == paginationAction.currentPage}">

                                            <span class ="radioDecorator">
                                                <input id="radioPageCount_${page}"
                                                       type="radio"
                                                       name="page" value="${page}">
                                                <button type="button"  class="href-nav-item-current"
                                                        onclick="
                                                                document.getElementById('radioPageCount_${page}').setAttribute('checked', true);
                                                                this.form.submit()">
                                                        ${page}
                                                </button>
                                            </span>
                                                    <%--<a &lt;%&ndash;class="href-nav-item"&ndash;%&gt;--%>
                                                    <%--class="href-nav-item-current"--%>
                                                    <%--&lt;%&ndash;href="selectPagination?maxResult=${paginationAction.maxResult}&page=${page}">${page}</a>&ndash;%&gt;--%>
                                                    <%--href="listNew?page=${page}">${page}</a>--%>

                                                </c:if>
                                                <c:if test="${page != paginationAction.currentPage}">

                                                    <%--<a class="href-nav-item"--%>
                                                    <%--&lt;%&ndash;href="selectPagination?maxResult=${paginationAction.maxResult}&page=${page}">${page}</a>&ndash;%&gt;--%>
                                                    <%--href="listNew?page=${page}">${page}</a>--%>
                                                    <span class ="radioDecorator">
                                                <input id="radioPageCount_${page}"
                                                       type="radio"
                                                       name="page" value="${page}">
                                                <button type="button"  class="href-nav-item"
                                                        onclick="
                                                                document.getElementById('radioPageCount_${page}').setAttribute('checked', true);
                                                                this.form.submit()" >
                                                        ${page}
                                                </button>
                                            </span>

                                                </c:if>

                                            </c:when>
                                            <c:otherwise>
                                                <span class="href-nav-item"> ... </span>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>

                                    <%--<a class="href-nav-item"--%>
                                    <%--href="selectPagination?page=${(paginationAction.currentPage != paginationAction.totalPages)? paginationAction.currentPage+1 : paginationAction.currentPage }"> -> </a>--%>

                                    <%--<a class="href-nav-item"--%>
                                    <%--href="selectPagination?page=${paginationAction.totalPages}"> >> </a>--%>

                                    <span class ="radioDecorator">
                                <input id="radioPageNext"
                                       type="radio"
                                       name="page" value="${(paginationAction.currentPage != paginationAction.totalPages)? paginationAction.currentPage+1 : paginationAction.currentPage }">
                                <button type="button"  class="href-nav-item"
                                        onclick="
                                      document.getElementById('radioPageNext').setAttribute('checked', true);
                                      this.form.submit()" >
                                    ->
                                </button>
                            </span>
                                    <span class ="radioDecorator">
                                <input id="radioPageLast"
                                       type="radio"
                                       name="page" value="${paginationAction.totalPages}">
                                <button type="button"  class="href-nav-item"
                                        onclick="
                                      document.getElementById('radioPageLast').setAttribute('checked', true);
                                      this.form.submit()" >
                                    >>
                                </button>
                            </span>

                                </c:if>
                            </div>
                        </td>
                        <td style="text-align: right">
                            <div id="selectMaxResult">
                                <%--<form action="selectPagination" method="get">--%>
                                <%--<form action="selectMaxResult" method="get">--%>
                                Показывать по :
                                <select type="text" name="maxResult"
                                        onchange="
                                    document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()"
                                ><%--multiple="true"--%>
                                    <c:forEach items="${selectShowListMaxResult}" var="tempMaxResult">
                                        <c:choose>
                                            <c:when test="${tempMaxResult.equals(paginationAction.maxResult)}">
                                                <option selected
                                                        value ="${tempMaxResult}">${tempMaxResult}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value ="${tempMaxResult}">${tempMaxResult}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <%--ПРЕДЫДУЩИЙ КОСТЫЛЬ<input type="hidden" name="page" value="1"></input>--%>
                                <%--<input type="submit" value="Войти на страницу" class="save" />--%>
                        </td>
                    </table>
                </div>

                <div id="list_actions">
                    <table>
                        <tr>
                            <th>(Mock)
                                <Br><input type="checkbox"></th>
                            <th>№</th>
                            <th>Тип действия<%--tempAction.actionType.name--%>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=actionType.name&desc=true"--%>
                                <%--href="orderDescAction?orderFieldName=actionType.name&desc=true" > /\ </a>--%>
                                <span class ="radioDecorator radio-actionType.name">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=actionType.name&desc=true"
                                           name="orderFieldName" value="actionType.name"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=actionType.name&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=actionType.name&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=actionType.name&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=actionType.name&desc=true').setAttribute('checked', true);
                                    document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=actionType.name&desc=false"--%>
                                <%--href="orderDescAction?orderFieldName=actionType.name&desc=false"> \/ </a>--%>
                                <span class ="radioDecorator radio-actionType.name">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=actionType.name&desc=false"
                                           name="orderFieldName" value="actionType.name"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=actionType.name&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=actionType.name&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=actionType.name&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=actionType.name&desc=false').setAttribute('checked', true);
                                document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Содержание<%--tempAction.content--%>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=content&desc=true"--%>
                                <%--href="orderDescAction?orderFieldName=content&desc=true"> /\ </a>--%>
                                <span class ="radioDecorator radio-content">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=content&desc=true"
                                           name="orderFieldName" value="content"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=content&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=content&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=content&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=content&desc=true').setAttribute('checked', true);
                                    document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=content&desc=false"--%>
                                <%--href="orderDescAction?orderFieldName=content&desc=false"> \/ </a>--%>
                                <span class ="radioDecorator radio-content">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=content&desc=false"
                                           name="orderFieldName" value="content"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=content&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=content&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=content&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=content&desc=false').setAttribute('checked', true);
                                document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Дата действия<%--tempAction.date.toString()--%>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=date&desc=true"--%>
                                <%--href="orderDescAction?orderFieldName=date&desc=true"> /\ </a>--%>
                                <span class ="radioDecorator radio-date">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=date&desc=true"
                                           name="orderFieldName" value="date"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=date&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=date&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=date&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=date&desc=true').setAttribute('checked', true);
                                    document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=date&desc=false"--%>
                                <%--href="orderDescAction?orderFieldName=date&desc=false"> \/ </a>--%>
                                <span class ="radioDecorator radio-date">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=date&desc=false"
                                           name="orderFieldName" value="date"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=date&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=date&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=date&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=date&desc=false').setAttribute('checked', true);
                                document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Исполнитель(can be need store IdUser)<%--tempAction.userByIdImplementor.firstName_lastName--%>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=userByIdImplementor.lastName&desc=true"--%>
                                <%--href="orderDescAction?orderFieldName=userByIdImplementor.lastName&desc=true"> /\ </a>--%>
                                <span class ="radioDecorator radio-userByIdImplementor.lastName">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=userByIdImplementor.lastName&desc=true"
                                           name="orderFieldName" value="userByIdImplementor.lastName"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=userByIdImplementor.lastName&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=userByIdImplementor.lastName&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=userByIdImplementor.lastName&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=userByIdImplementor.lastName&desc=true').setAttribute('checked', true);
                                    document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=userByIdImplementor.lastName&desc=false"--%>
                                <%--href="orderDescAction?orderFieldName=userByIdImplementor.lastName&desc=false"> \/ </a>--%>
                                <span class ="radioDecorator radio-userByIdImplementor.lastName">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=userByIdImplementor.lastName&desc=false"
                                           name="orderFieldName" value="userByIdImplementor.lastName"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=userByIdImplementor.lastName&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=userByIdImplementor.lastName&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=userByIdImplementor.lastName&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=userByIdImplementor.lastName&desc=false').setAttribute('checked', true);
                                document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>

                            </th>
                            <th>Подразделение<%--Id--%>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=userByIdImplementor.organization.name&desc=true"--%>
                                <%--href="orderDescAction?orderFieldName=userByIdImplementor.organization.name&desc=true"> /\ </a>--%>
                                <span class ="radioDecorator radio-userByIdImplementor.organization.name">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=userByIdImplementor.organization.name&desc=true"
                                           name="orderFieldName" value="userByIdImplementor.organization.name"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=userByIdImplementor.organization.name&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=userByIdImplementor.organization.name&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=userByIdImplementor.organization.name&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=userByIdImplementor.organization.name&desc=true').setAttribute('checked', true);
                                    document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=userByIdImplementor.organization.name&desc=false"--%>
                                <%--href="orderDescAction?orderFieldName=userByIdImplementor.organization.name&desc=false"> \/ </a>--%>
                                <span class ="radioDecorator radio-userByIdImplementor.organization.name">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=userByIdImplementor.organization.name&desc=false"
                                           name="orderFieldName" value="userByIdImplementor.organization.name"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=userByIdImplementor.organization.name&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=userByIdImplementor.organization.name&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=userByIdImplementor.organization.name&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=userByIdImplementor.organization.name&desc=false').setAttribute('checked', true);
                                document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Статус после изменения<%--Id--%>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=notificationStatusAfterProcessing.name&desc=true"--%>
                                <%--href="orderDescAction?orderFieldName=notificationStatusAfterProcessing.name&desc=true"> /\ </a>--%>
                                <span class ="radioDecorator radio-notificationStatusAfterProcessing.name">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=notificationStatusAfterProcessing.name&desc=true"
                                           name="orderFieldName" value="notificationStatusAfterProcessing.name"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=notificationStatusAfterProcessing.name&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=notificationStatusAfterProcessing.name&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=notificationStatusAfterProcessing.name&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=notificationStatusAfterProcessing.name&desc=true').setAttribute('checked', true);
                                    document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <%--<a class="href-nav-item" id="orderFieldName=notificationStatusAfterProcessing.name&desc=false"--%>
                                <%--href="orderDescAction?orderFieldName=notificationStatusAfterProcessing.name&desc=false"> \/ </a>--%>
                                <span class ="radioDecorator radio-notificationStatusAfterProcessing.name">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=notificationStatusAfterProcessing.name&desc=false"
                                           name="orderFieldName" value="notificationStatusAfterProcessing.name"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=notificationStatusAfterProcessing.name&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=notificationStatusAfterProcessing.name&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=notificationStatusAfterProcessing.name&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=notificationStatusAfterProcessing.name&desc=false').setAttribute('checked', true);
                                document.getElementById('selectedNewResultAndNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                        </tr>
                        <c:forEach var="tempAction" items="${listAction}" varStatus="actionLoopCount" >
                            <tr>
                                <td><input type="checkbox"></td>
                                <td>${actionLoopCount.count + (paginationAction.currentPage-1)*paginationAction.maxResult}</td>
                                <td>${tempAction.actionType.name}</td>
                                <td>${tempAction.content}</td>
                                <td>${tempAction.date.toString()}</td>
                                <td>${tempAction.userByIdImplementor.firstName} ${tempAction.userByIdImplementor.lastName}</td>
                                <td>${tempAction.userByIdImplementor.organization.name}</td>
                                <td>${tempAction.notificationStatusAfterProcessing.name}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <input id="selectedNewResultAndNeedSetFirstPage"
                       type="hidden"
                       name="selectedNewResultAndNeedSetFirstPage" value="false"></input>

        </div>
    </div>

    </form>

</div>

<div id="return">
    <a href="${pageContext.request.contextPath}/cabinet/current_state"> Back to list </a>
</div>

<script>
    function changeVisibilityById(idDivVisibility, idButton) {
        var divVisibility = document.getElementById(idDivVisibility);
        var button = document.getElementById(idButton);

        divVisibility.style.display = (divVisibility.style.display=='none')? '' : 'none'
        button.innerHTML  = (button.innerHTML == "/\\") ? "\\/" : "/\\";

        button.className = (button.className == "href-nav-item" ? "href-nav-item-current" : "href-nav-item");
    }
    // window.onload = function() {
    //     var divVisibilityS = document.getElementsByClassName("div-default-visibility-false");
    //     var buttonS = document.getElementsByClassName("button-default-visibility-false");
    //
    //     for (var i = 0; i < divVisibilityS.length; i++) {
    //         divVisibilityS[i].style.display = (divVisibilityS[i].style.display=='none')? '' : 'none'
    //     }
    //     for (var i = 0; i < buttonS.length; i++) {
    //         buttonS[i].innerHTML  = (buttonS[i].innerHTML == "/\\") ? "\\/" : "/\\";
    //        // buttonS[i].className = (buttonS[i].className == "href-nav-item") ? "href-nav-item-current" : "href-nav-item";
    //         if( buttonS[i].classList.contains("href-nav-item") ){
    //             buttonS[i].classList.toggle("href-nav-item");
    //             buttonS[i].classList.toggle("href-nav-item-current");
    //         }
    //
    //     }
    // };


    document.getElementById("orderFieldName=${orderFieldNameAction}&desc=${descAction}").classList.add('href-nav-item-current');

    document.getElementById("radio-orderFieldName-orderFieldName=${orderFieldNameAction}&desc=${descAction}").setAttribute('checked', true);
    document.getElementById("radio-desc-orderFieldName=${orderFieldNameAction}&desc=${descAction}").setAttribute('checked', true);

    //todo; trows Excpetion if element=null //JavaScript simple do not run next command line, ПОЭТОМУ this line is last
    document.getElementById("radioPageCount_${paginationAction.currentPage}").setAttribute('checked', true);

</script>
</body>
</html>