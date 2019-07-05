<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Notification CRUD Application</title>
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
                            <option selected value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>
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
                        <input onchange="this.form.submit()" type="checkbox" name="id" class= "checkboxStatuses4select" value="${tempStatus.id}" checked >${tempStatus.name}</input><Br>
                    </c:when>
                    <c:otherwise>
                        <input onchange="this.form.submit()" type="checkbox" name="id" class= "checkboxStatuses4select" value="${tempStatus.id}">${tempStatus.name}</input><Br>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <Br>
            <c:choose>
                <c:when test="${showProcessed}">
                    <input onchange="this.form.submit()" type="checkbox" name="showProcessed" class= "checkboxShowProcessed" value="checked" checked>Показывать архивные уведомления</input><Br>
                    <%--<input type="hidden" name="showProcessed" value="${!showProcessed}" checked></input>--%>
                    <%--<button id="Archive" type="submit" style="background: green">Архив</button><Br>--%>
                </c:when>
                <c:otherwise>
                    <input onchange="this.form.submit()" type="checkbox" name="showProcessed" class= "checkboxShowProcessed" value="checked">Показывать архивные уведомления</input><Br>
                    <%--<input type="hidden" name="showProcessed" value="${!showProcessed}"></input>--%>
                    <%--<button id="Archive" type="submit" style="background: red">Архив</button><Br>--%>
                </c:otherwise>
            </c:choose>

            <%--<Br>Временой фильтр(я считаю что это 1-ой по приоритету фильтр)<Br>--%>
            <%--<c:choose>--%>
            <%--<c:when test="${flagTimeFilter}">--%>
            <%--<input type="checkbox" name="flagTimeFilter" class= "checkbox" value="checked" checked>use TimeFilter</input><Br>--%>
            <%--&lt;%&ndash;<input type="hidden" name="showProcessed" value="${!showProcessed}" checked></input>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<button id="Archive" type="submit" style="background: green">Архив</button><Br>&ndash;%&gt;--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<input type="checkbox" name="flagTimeFilter" class= "checkbox" value="checked">use TimeFilter</input><Br>--%>
            <%--&lt;%&ndash;<input type="hidden" name="showProcessed" value="${!showProcessed}"></input>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<button id="Archive" type="submit" style="background: red">Архив</button><Br>&ndash;%&gt;--%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--<select type="text" name="idSelectUser" >&lt;%&ndash;multiple="true"&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option selected value ="${user.id}">(заданный)${user.firstName} ${user.lastName}</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:options items="${user_list}"  itemLabel="name" itemValue="id" />&ndash;%&gt;--%>
            <%--<c:forEach items="${user_list}" var="tempUser">--%>
            <%--<option value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>

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

        <Br>Продвинутный Фильтр
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

        <input type="button" value="Add Notification"
               onclick="window.location.href='add'; return false;"
               class="add-button"
        />

        <!--  saveOrUpdate a search box -->
        <form:form action="search" method="POST">
            Search notification: <input type="text" name="theSearchName" />

            <input type="submit" value="Search" class="add-button" />
        </form:form>

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

                <c:url var="updateLink" value="showFormForUpdate">
                    <c:param name="notificationId" value="${tempNotification.id}"/>
                </c:url>

                <c:url var="deleteLink" value="delete">
                    <c:param name="notificationId" value="${tempNotification.id}"/>
                </c:url>

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
                        <a href="${updateLink}">Update</a>
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) return false">Delete</a>
                        (сылка-Юперейти к содержимому действий)
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>