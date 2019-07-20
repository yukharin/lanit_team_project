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

        span.radioPage > input{ /* HIDE RADIO */
            visibility: hidden; /* Makes input not-clickable */
            position: absolute; /* Remove input from document flow */
        }
        span.radioPage > input + button{ /* IMAGE STYLES */
            cursor:pointer;
            border:2px solid transparent;
        }
        span.radioPage> input:checked + button{ /* (RADIO CHECKED) IMAGE STYLES */
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
    <form action="listNew"  method="get">
        <div id="filters">
            <%--<form action="filterByNotificStatus"  method="get">--%>
            Фильтры:<Br>
            <table>
                <td style="text-align: left; width: 30%;">
                    <c:forEach items="${statuses4select}" var="tempStatus">
                        <c:choose>
                            <c:when test="${checked_list.contains(tempStatus)}">
                                <input checked
                                       onchange="this.form.submit()" type="checkbox" name="idFilterStatus" class= "checkboxStatuses4select" value="${tempStatus.id}"  >${tempStatus.name}</input><Br>
                            </c:when>
                            <c:otherwise>
                                <input onchange="this.form.submit()" type="checkbox" name="idFilterStatus" class= "checkboxStatuses4select" value="${tempStatus.id}">${tempStatus.name}</input><Br>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${showArchive}">
                            <input checked
                                   onchange="this.form.submit()" type="checkbox" name="showArchive" class= "checkboxshowArchive" value="checked" >Показывать архивные уведомления</input><Br>
                            <%--<input type="hidden" name="showArchive" value="${!showArchive}" checked></input>--%>
                            <%--<button id="Archive" type="submit" style="background: green">Архив</button><Br>--%>
                        </c:when>
                        <c:otherwise>
                            <input onchange="this.form.submit()" type="checkbox" name="showArchive" class= "checkboxshowArchive" value="checked">Показывать архивные уведомления</input><Br>
                            <%--<input type="hidden" name="showArchive" value="${!showArchive}"></input>--%>
                            <%--<button id="Archive" type="submit" style="background: red">Архив</button><Br>--%>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <input type="checkbox">(Mock)Быстрый фильтр<Br>
                    <select type="text" name="selectFastFilter" ><%--multiple="true"--%>
                        <c:forEach items="${listFastFilter}" var="tempFilter">
                            <c:choose>
                                <c:when test="${currentFastFilter.id.equals(tempFilter.id)}">
                                    <option selected
                                            value ="${tempFilter.id}">${tempFilter.description}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value ="${tempFilter.id}">${tempFilter.description}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    (Mock)Кнопка «Показать дополнительные фильтры»
                </td>
            </table>
            <button type="form.submit" <%--class="green_button"--%> style="background-color: #4CAF50; color: white; display: inline-block;">Применить фильтр</button>
            <button type="form.submit" style="background-color: #666666; color: white; display: inline-block;"
                    onclick="setChecked()" >Отменить фильтр</button>
            <script>
                function setChecked() {
                    var mas = document.getElementsByClassName("checkboxStatuses4select");
                    for (var i=0;i<mas.length;i++){
                        mas[i].checked = true;//.setAttribute('checked', 'checked');
                    }
                    var mas = document.getElementsByClassName("checkboxshowArchive");
                    for (var i=0;i<mas.length;i++){
                        mas[i].checked = false;//.setAttribute('checked', 'checked');
                    }
                }
            </script>

            <%--</form>--%>
        </div>

        <div id="pagination">
            <table>
                <td style="text-align: left">
                    Найдено записей: ${pagination.totalRecords}<Br>
                </td>
                <td style="text-align: center">
                    <div id="page-navigator">
                        <c:if test="${pagination.totalPages > 1}">


                            <span class ="radioPage">
                                <input type="radio" id="radioPageFirst"
                                       name="page" value="1">
                              <button class="href-nav-item"
                                      onclick="document.getElementById('radioPageFirst').setAttribute('checked', true); this.form.submit()" > << </button>
                            </span>

                            <span class ="radioPage">
                                <input type="radio" id="radioPagePrev"
                                       name="page" value="${(pagination.currentPage != 1) ? pagination.currentPage-1 : 1 }"
                                       onchange="this.setChecked(); this.form.submit()" >
                              <button class="href-nav-item"
                                      onclick="document.getElementById('radioPagePrev').setAttribute('checked', true); this.form.submit()" > <- </button>
                            </span>

                            <%--<a class="href-nav-item"--%>
                            <%--&lt;%&ndash;href="selectPagination?maxResult=${pagination.maxResult}&page=1"> << </a>&ndash;%&gt;--%>
                            <%--href="listNew?page=1"> << </a>--%>

                            <%--<a class="href-nav-item"--%>
                            <%--&lt;%&ndash;href="selectPagination?maxResult=${pagination.maxResult}&page=${(pagination.currentPage != 1) ? pagination.currentPage-1 : 1 }"> <- </a>&ndash;%&gt;--%>
                            <%--href="listNew?page=${(pagination.currentPage != 1) ? pagination.currentPage-1 : 1 }"> <- </a>--%>

                            <c:forEach items="${pagination.navigationPages}" var = "page">
                                <c:choose>
                                    <c:when test="${page != -1 }">
                                        <c:if test="${page == pagination.currentPage}">
                                            <span class ="radioPage">
                                                <input type="radio" id="radioPageCount_${page}"
                                                       name="page" value="${page}"
                                                       onchange="this.setChecked(); this.form.submit()">
                                                <button class="href-nav-item-current"
                                                        onclick="document.getElementById('radioPageCount_${page}').setAttribute('checked', true); this.form.submit()" >
                                                        ${page}
                                                </button>
                                            </span>
                                            <%--<a &lt;%&ndash;class="href-nav-item"&ndash;%&gt;--%>
                                            <%--class="href-nav-item-current"--%>
                                            <%--&lt;%&ndash;href="selectPagination?maxResult=${pagination.maxResult}&page=${page}">${page}</a>&ndash;%&gt;--%>
                                            <%--href="listNew?page=${page}">${page}</a>--%>
                                        </c:if>
                                        <c:if test="${page != pagination.currentPage}">
                                            <%--<a class="href-nav-item"--%>
                                                <%--&lt;%&ndash;href="selectPagination?maxResult=${pagination.maxResult}&page=${page}">${page}</a>&ndash;%&gt;--%>
                                               <%--href="listNew?page=${page}">${page}</a>--%>
                                            <span class ="radioPage">
                                                <input type="radio" id="radioPageCount_${page}"
                                                       name="page" value="${page}"
                                                       onchange="this.setChecked(); this.form.submit()">
                                                <button class="href-nav-item"
                                                        onclick="document.getElementById('radioPageCount_${page}').setAttribute('checked', true); this.form.submit()" >
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
                            <%--href="selectPagination?page=${(pagination.currentPage != pagination.totalPages)? pagination.currentPage+1 : pagination.currentPage }"> -> </a>--%>

                            <%--<a class="href-nav-item"--%>
                            <%--href="selectPagination?page=${pagination.totalPages}"> >> </a>--%>

                            <span class ="radioPage">
                                <input type="radio" id="radioPageNext"
                                       name="page" value="${(pagination.currentPage != pagination.totalPages)? pagination.currentPage+1 : pagination.currentPage }">
                              <button class="href-nav-item"
                                      onclick="document.getElementById('radioPageNext').setAttribute('checked', true); this.form.submit()" > -> </button>
                            </span>
                            <span class ="radioPage">
                                <input type="radio" id="radioPageLast"
                                       name="page" value="${pagination.totalPages}"
                                       onchange="this.form.submit()" >
                              <button class="href-nav-item"
                                      onclick="document.getElementById('radioPageLast').setAttribute('checked', true); this.form.submit()" > >> </button>
                            </span>

                        </c:if>
                    </div>
                </td>
                <td style="text-align: right">
                    <div id="selectMaxResult">
                        <%--<form action="selectPagination" method="get">--%>
                        Показывать по :
                        <select type="text" name="maxResult" onchange="this.form.submit()" ><%--multiple="true"--%>
                            <c:forEach items="${selectShowListMaxResult}" var="tempMaxResult">
                                <c:choose>
                                    <c:when test="${tempMaxResult.equals(pagination.maxResult)}">
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
                        <%--</form>--%>
                    </div>
                </td>
            </table>
        </div>

        <div id="list_notifications">
            <table>
                <tr>
                    <th>(Mock)
                        <Br><input type="checkbox"></th>
                    <th>№</th>
                    <th>Вид уведомления<%--notificationType--%>
                        <Br><a class="href-nav-item" id="orderFieldName=notificationType&desc=true"
                               href="orderDesc?orderFieldName=notificationType&desc=true" > /\ </a>
                        <Br><a class="href-nav-item" id="orderFieldName=notificationType&desc=false"
                               href="orderDesc?orderFieldName=notificationType&desc=false"> \/ </a>
                    </th>
                    <th>Заказчик<%--organization.NAME--%>
                        <Br><a class="href-nav-item" id="orderFieldName=organization.name&desc=true"
                               href="orderDesc?orderFieldName=organization.name&desc=true"> /\ </a>
                        <Br><a class="href-nav-item" id="orderFieldName=organization.name&desc=false"
                               href="orderDesc?orderFieldName=organization.name&desc=false"> \/ </a>
                    </th>
                    <th>Срок предоставления ответа<%--dateResponse--%>
                        <Br><a class="href-nav-item" id="orderFieldName=dateResponse&desc=true"
                               href="orderDesc?orderFieldName=dateResponse&desc=true"> /\ </a>
                        <Br><a class="href-nav-item" id="orderFieldName=dateResponse&desc=false"
                               href="orderDesc?orderFieldName=dateResponse&desc=false"> \/ </a>
                    </th>
                    <th>Статус обработки уведомления<%--notificationStatus--%>
                        <Br><a class="href-nav-item" id="orderFieldName=notificationStatus.name&desc=true"
                               href="orderDesc?orderFieldName=notificationStatus.name&desc=true"> /\ </a>
                        <Br><a class="href-nav-item" id="orderFieldName=notificationStatus.name&desc=false"
                               href="orderDesc?orderFieldName=notificationStatus.name&desc=false"> \/ </a>
                    </th>
                    <th>Номер уведомления<%--Id--%>
                        <Br><a class="href-nav-item" id="orderFieldName=id&desc=true"
                               href="orderDesc?orderFieldName=id&desc=true"> /\ </a>
                        <Br><a class="href-nav-item" id="orderFieldName=id&desc=false"
                               href="orderDesc?orderFieldName=id&desc=false"> \/ </a>
                    </th>
                    <th>Дата получения уведомления<%--dateReceived--%>
                        <Br><a class="href-nav-item" id="orderFieldName=dateReceived&desc=true"
                               href="orderDesc?orderFieldName=dateReceived&desc=true"> /\ </a>
                        <Br><a class="href-nav-item" id="orderFieldName=dateReceived&desc=false"
                               href="orderDesc?orderFieldName=dateReceived&desc=false"> \/ </a>
                    </th>
                    <th>Номер письма<%--letterNumber--%>
                        <Br><a class="href-nav-item" id="orderFieldName=letterNumber&desc=true"
                               href="orderDesc?orderFieldName=letterNumber&desc=true"> /\ </a>
                        <Br><a class="href-nav-item" id="orderFieldName=letterNumber&desc=false"
                               href="orderDesc?orderFieldName=letterNumber&desc=false"> \/ </a>
                    </th>
                    <th>Действия</th>
                </tr>
                <c:forEach var="tempNotification" items="${notific_list}" varStatus="notificLoopCount" >
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>${notificLoopCount.count + (pagination.currentPage-1)*pagination.maxResult}
                        </td>
                        <td>${tempNotification.notificationType}</td>
                        <td>${tempNotification.organization.name}</td>
                        <td>${tempNotification.dateResponse.toString()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.organization.government}">
                                    <form action="editStatus" method="get">
                                        <input type="hidden" name="idNotification" value="${tempNotification.id}"></input>
                                        <select type="text" name="idStatus" onchange="this.form.submit()" >
                                            <c:forEach items="${statuses4select}" var="status">
                                                <c:choose>
                                                    <c:when test="${tempNotification.notificationStatus.id.equals(status.id)}">
                                                        <option selected
                                                                value ="${status.id}">${status.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value ="${status.id}">${status.name}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                            <%--<input type="submit" value="Применить изменения" class="save" />--%>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    ${tempNotification.notificationStatus.name}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${tempNotification.id}</td>
                        <td>${tempNotification.dateReceived.toString()}</td>
                        <td>${tempNotification.letterNumber}</td>
                        <td>
                            Кол-во: ${tempNotification.actions.size()}
                            <Br>
                            <c:url var="addLink" value="more">
                                <c:param name="notificationId" value="${tempNotification.id}"/>
                            </c:url>
                            <a href="${addLink}">More</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </form>
</div>

<script>
    document.getElementById("orderFieldName=${orderFieldName}&desc=${desc}").classList.add('href-nav-item-current');
</script>
</body>
</html>