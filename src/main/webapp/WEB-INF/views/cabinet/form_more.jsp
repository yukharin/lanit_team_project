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

                <Br><Br>
                <a class="href-nav-item"
                   href="${pageContext.request.contextPath}/user/list"> Настройка пользователей </a>
                <Br>
            </form>
        </th>
    </table>
</div>

<div id="content">

    <div id="common_info_about_notification">
        <h5 style="text-transform: uppercase;">
            ${currentNotification.notificationType}
            <button onclick="changeVisibilityById('controlled_visibility-common_info_about_notification', 'button-common_info_about_notification')"
                    id = "button-common_info_about_notification"
                    class="href-nav-item"> \/ </button>
        </h5>
        <div class="controlled_visibility" id="controlled_visibility-common_info_about_notification">
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
            <button onclick="changeVisibilityById('controlled_visibility-answer_preparation', 'button-answer_preparation')"
                    id = "button-answer_preparation"
                    class="href-nav-item"> \/ </button>
        </h5>
        <div class="controlled_visibility" id="controlled_visibility-answer_preparation">
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
            <button onclick="changeVisibilityById('controlled_visibility-actions', 'button-actions')"
                    id = "button-actions"
                    class="href-nav-item"> \/ </button>
        </h5>
        <div class="controlled_visibility" id="controlled_visibility-actions">
            <div id="add_action">

                <form action="addAction"  method="get">
                    <button type="form.submit" <%--class="green_button"--%> style="background-color: #4CAF50; color: white; display: inline-block;">
                        Добавить действие</button>
                    <%--<input type="hidden" name="notificationId" value="${currentNotification.id}"></input>--%>
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
                                            <c:when test="${currentNotification.notificationStatus.id.equals(tempStatus.id)}">
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
                                <textarea id="story" name="content" maxlength="300"
                                          rows="4" cols="50"></textarea>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

            <div id="paginationAction">
                <table >
                    <td style="text-align: left">
                        Найдено записей: ${paginationAction.totalRecords}<Br>
                    </td>
                    <td style="text-align: center">
                        <div id="page-navigator">
                            <%--TODO set form[1)maxResult=${paginationAction.maxResult} 2)page=x--%>
                            <c:if test="${paginationAction.totalPages > 1}">

                                <a class="href-nav-item"
                                   href="productListAction?maxResult=${paginationAction.maxResult}&page=1"> << </a>

                                <a class="href-nav-item"
                                   href="productListAction?maxResult=${paginationAction.maxResult}&page=${(paginationAction.currentPage != 1) ? paginationAction.currentPage-1 : 1 }"> <- </a>

                                <c:forEach items="${paginationAction.navigationPages}" var = "page">
                                    <c:choose>
                                        <c:when test="${page != -1 }">
                                            <c:if test="${page == paginationAction.currentPage}">
                                                <a class="href-nav-item-current"
                                                   href="productListAction?maxResult=${paginationAction.maxResult}&page=${page}">${page}</a>
                                            </c:if>
                                            <c:if test="${page != paginationAction.currentPage}">
                                                <a class="href-nav-item"
                                                   href="productListAction?maxResult=${paginationAction.maxResult}&page=${page}">${page}</a>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="href-nav-item"> ... </span>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>

                                <a class="href-nav-item"
                                   href="productListAction?maxResult=${paginationAction.maxResult}&page=${(paginationAction.currentPage != paginationAction.totalPages)? paginationAction.currentPage+1 : paginationAction.currentPage }"> -> </a>

                                <a class="href-nav-item"
                                   href="productListAction?maxResult=${paginationAction.maxResult}&page=${paginationAction.totalPages}"> >> </a>

                            </c:if>
                        </div>
                    </td>
                    <td style="text-align: right">
                        <div id="selectMaxResult">
                            <form action="productListAction" method="get">
                                Показывать по :
                                <select type="text" name="maxResult" onchange="this.form.submit()" ><%--multiple="true"--%>
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
                                <input type="hidden" name="page" value="1"></input>
                                <%--<input type="submit" value="Войти на страницу" class="save" />--%>
                            </form>
                        </div>
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
                            <Br><a class="href-nav-item" id="orderFieldName=actionType&desc=true"
                                   href="orderDescAction?orderFieldName=actionType&desc=true" > /\ </a>
                            <Br><a class="href-nav-item" id="orderFieldName=actionType&desc=false"
                                   href="orderDescAction?orderFieldName=actionType&desc=false"> \/ </a>
                        </th>
                        <th>Содержание<%--tempAction.content--%>
                            <Br><a class="href-nav-item" id="orderFieldName=content&desc=true"
                                   href="orderDescAction?orderFieldName=content&desc=true"> /\ </a>
                            <Br><a class="href-nav-item" id="orderFieldName=content&desc=false"
                                   href="orderDescAction?orderFieldName=content&desc=false"> \/ </a>
                        </th>
                        <th>Дата действия<%--tempAction.date.toString()--%>
                            <Br><a class="href-nav-item" id="orderFieldName=date&desc=true"
                                   href="orderDescAction?orderFieldName=date&desc=true"> /\ </a>
                            <Br><a class="href-nav-item" id="orderFieldName=date&desc=false"
                                   href="orderDescAction?orderFieldName=date&desc=false"> \/ </a>
                        </th>
                        <th>Исполнитель(can be need store IdUser)<%--tempAction.userByIdImplementor.firstName_lastName--%>
                            <Br><a class="href-nav-item" id="orderFieldName=userByIdImplementor.lastName&desc=true"
                                   href="orderDescAction?orderFieldName=userByIdImplementor.lastName&desc=true"> /\ </a>
                            <Br><a class="href-nav-item" id="orderFieldName=userByIdImplementor.lastName&desc=false"
                                   href="orderDescAction?orderFieldName=userByIdImplementor.lastName&desc=false"> \/ </a>
                        </th>
                        <th>Подразделение<%--Id--%>
                            <Br><a class="href-nav-item" id="orderFieldName=userByIdImplementor.organization.name&desc=true"
                                   href="orderDescAction?orderFieldName=userByIdImplementor.organization.name&desc=true"> /\ </a>
                            <Br><a class="href-nav-item" id="orderFieldName=userByIdImplementor.organization.name&desc=false"
                                   href="orderDescAction?orderFieldName=userByIdImplementor.organization.name&desc=false"> \/ </a>
                        </th>
                        <th>Статус после изменения<%--Id--%>
                            <Br>
                            <a class="href-nav-item" id="orderFieldName=userByIdImplementor.actionType.name&desc=true"
                               href="orderDescAction?orderFieldName=userByIdImplementor.actionType.name&desc=true"> /\ </a>
                            <Br>
                            <a class="href-nav-item" id="orderFieldName=userByIdImplementor.actionType.name&desc=false"
                               href="orderDescAction?orderFieldName=userByIdImplementor.actionType.name&desc=false"> \/ </a>
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
        </div>
    </div>
</div>

<div id="return">
    <a href="${pageContext.request.contextPath}/cabinet/list"> Back to list </a>
</div>

<script>
    document.getElementById("orderFieldName=${orderFieldNameAction}&desc=${descAction}").classList.add('href-nav-item-current');

    function changeVisibilityById(idDivVisibility, idButton) {
        var divVisibility = document.getElementById(idDivVisibility);
        var button = document.getElementById(idButton);

        divVisibility.style.display = (divVisibility.style.display=='none')? '' : 'none'
        button.innerHTML  = (button.innerHTML == "/\\") ? "\\/" : "/\\";

        button.className = (button.className == "href-nav-item" ? "href-nav-item-current" : "href-nav-item");
    }
</script>
</body>
</html>