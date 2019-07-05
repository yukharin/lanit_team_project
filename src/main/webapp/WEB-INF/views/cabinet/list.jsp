<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Notification CRUD Application</title>
    <%--<style>--%>
        <%--a.nav-item { background-color: red; }--%>
    <%--</style>--%>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h5>ЛИЧНЫЙ КАБИНЕТ ЗАКАЗЧИКА</h5>

        <c:if test="${user == null}">
            <p style="color: red">(ОБЯЗАТЕЛЬНО ВЫБЕРИТЕ ПОЛЬЗОВАТЕЛЯ)</p>
        </c:if>

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

        Oрганизация: ${user.organization.name}<Br>

        <Br>
        <form action="filterByNotificStatus"  method="get">
            Фильтры по словарю notificationStatus<Br>
            <c:forEach items="${statuses4select}" var="tempStatus">
                <c:choose>
                    <c:when test="${checked_list.contains(tempStatus)}">
                        <input checked
                               onchange="this.form.submit()" type="checkbox" name="id" class= "checkboxStatuses4select" value="${tempStatus.id}"  >${tempStatus.name}</input><Br>
                    </c:when>
                    <c:otherwise>
                        <input onchange="this.form.submit()" type="checkbox" name="id" class= "checkboxStatuses4select" value="${tempStatus.id}">${tempStatus.name}</input><Br>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <Br>
            <c:choose>
                <c:when test="${showProcessed}">
                    <input checked
                           onchange="this.form.submit()" type="checkbox" name="showProcessed" class= "checkboxShowProcessed" value="checked" >Показывать архивные уведомления</input><Br>
                    <%--<input type="hidden" name="showProcessed" value="${!showProcessed}" checked></input>--%>
                    <%--<button id="Archive" type="submit" style="background: green">Архив</button><Br>--%>
                </c:when>
                <c:otherwise>
                    <input onchange="this.form.submit()" type="checkbox" name="showProcessed" class= "checkboxShowProcessed" value="checked">Показывать архивные уведомления</input><Br>
                    <%--<input type="hidden" name="showProcessed" value="${!showProcessed}"></input>--%>
                    <%--<button id="Archive" type="submit" style="background: red">Архив</button><Br>--%>
                </c:otherwise>
            </c:choose>

            <Br>(Mock)Быстрый фильтр<Br>
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
            <Br>
            <button type="form.submit">Применить фильтр</button>
            <button type="form.submit" onclick="setChecked()">Отменить фильтр</button>
            <script>
                function setChecked() {
                    var mas = document.getElementsByClassName("checkboxStatuses4select");
                    for (var i=0;i<mas.length;i++){
                        mas[i].checked = true;//.setAttribute('checked', 'checked');
                    }
                    var mas = document.getElementsByClassName("checkboxShowProcessed");
                    for (var i=0;i<mas.length;i++){
                        mas[i].checked = false;//.setAttribute('checked', 'checked');
                    }
                }
            </script>

        </form>

        <Br>Кнопка «Показать дополнительные фильтры»
        <Br>
        <Br>Notification Order BY [Срок предоставления ответа] DESK
        <Br>Пагинация таблицы (общее кол-во найденных записей, + ссылки на другие страницы, + выбор по скольку записей за раз показывать)
        <Br>Регулируемая сортирвка над каждым полем
        <Br>Возможность отметить "галкой" записи (зачем не знаю)
        <Br>обычный столбец нумерации
        <Br>обычный Notification(с ограничениями выше)
        <Br>подсветка (в спецификации) красным=DeadLine(+countDiff) серая=Отработанное new=Полужирные
        <Br>каким нибудь образом возможность нажать на уведомление чтоб можно было перейти на форму с её действиями(Action c выборкой по Notific_ID)(скорей всего двойной клик)

    </div>
</div>

<div id="container">
    <div id="content">

        Найдено записей ${paginationProduct.totalRecords}<Br>
        Показывать по :
        <a href="productList?maxResult=1&page=1" class="nav-item"> 1 </a>
        <a href="productList?maxResult=5&page=1" class="nav-item"> 5 </a>
        <a href="productList?maxResult=10&page=1" class="nav-item"> 10 </a>
        <a href="productList?maxResult=25&page=1" class="nav-item"> 25 </a>
        <a href="productList?maxResult=50&page=1" class="nav-item"> 50 </a>
        <a href="productList?maxResult=100&page=1" class="nav-item"> 100 </a>
        <Br>
    <%--<select id = "selectPagination" type="text" name="selectMaxResult" onchange="set()">&lt;%&ndash;multiple="true"&ndash;%&gt;--%>
            <%--<option selected--%>
                    <%--value ="1">1</option>--%>
            <%--<option value ="5">5</option>--%>
            <%--<option value ="10">10</option>--%>
            <%--<option value ="25">25</option>--%>
            <%--<option value ="50">50</option>--%>
            <%--<option value ="100">100</option>--%>

            <%--<script>--%>
                <%--function set() {--%>
                    <%--window.location.href="productList?maxResult="+document.getElementById("selectPagination")+"&page=1";--%>
                <%--}--%>
            <%--</script>--%>

        </select>

        <c:if test="${paginationProduct.totalPages > 1}">
            <div class="page-navigator">

                <a href="productList?maxResult=1&page=1" class="nav-item"> << </a>

                <a href="productList?maxResult=1&page=${(paginationProduct.currentPage != 1) ? paginationProduct.currentPage-1 : 1 }"
                   class="nav-item"> <- </a>

                <c:forEach items="${paginationProduct.navigationPages}" var = "page">
                    <c:choose>
                        <c:when test="${page != -1 }">
                            <a href="productList?maxResult=1&page=${page}" class="nav-item">${page}</a>
                        </c:when>
                        <c:otherwise>
                            <span class="nav-item"> ... </span>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <a href="productList?maxResult=1&page=${(paginationProduct.currentPage != paginationProduct.totalPages)? paginationProduct.currentPage+1 : paginationProduct.currentPage }"
                   class="nav-item"> -> </a>

                <a href="productList?maxResult=1&page=${paginationProduct.totalPages}" class="nav-item"> >> </a>

            </div>
        </c:if>

        <table>
            <tr>
                <th>Id</th>
                <th>organization.NAME</th>
                <th>notificationType</th>
                <th>dateReceived</th>
                <th>dateResponse</th>
                <th>letterNumber</th>
                <th>userByIdUserCuratorGos</th>
                <th>userByIdUserImplementor</th>
                <th>notificationStatus</th>
                <th>f(x)</th>
            </tr>

            <c:forEach var="tempNotification" items="${notific_list}">
                <tr>
                    <td>${tempNotification.id}</td>
                    <td>${tempNotification.organization.name}</td>
                    <td>${tempNotification.notificationType}</td>
                    <td>${tempNotification.dateReceived.toString()}</td>
                    <td>${tempNotification.dateResponse.toString()}</td>
                    <td>${tempNotification.letterNumber}</td>
                    <td>${tempNotification.userByIdUserCuratorGos}</td>
                    <td>${tempNotification.userByIdUserImplementor}</td>
                    <td>${tempNotification.notificationStatus.name}</td>
                    <td>
                        <c:url var="updateLink" value="showFormForUpdate">
                            <c:param name="notificationId" value="${tempNotification.id}"/>
                        </c:url>
                        <a href="${updateLink}">Update</a>

                        <c:url var="deleteLink" value="delete">
                            <c:param name="notificationId" value="${tempNotification.id}"/>
                        </c:url>
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) return false">Delete</a>
                        (сылка->перейти к содержимому действий)
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>