<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Notification CRUD Application</title>
    <!--link type="text/css" rel="stylesheet" href="//{pageContext.request.contextPath}/resources/css/style.css" /-->
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h5>ЛИЧНЫЙ КАБИНЕТ ЗАКАЗЧИКА</h5>
        <h5>Пользователь:
            <c:choose>
                <c:when test="${user != null}">
                    ${user.firstName} ${user.lastName} (+ кнопки настроек профиля пользователя)
                </c:when>
                <c:otherwise>
                    <dl style="color: red">(ОБЯЗАТЕЛЬНО ВЫБЕРИТЕ ПОЛЬЗОВАТЕЛЯ)</dl>
                </c:otherwise>
            </c:choose>
        </h5>
        ${user.firstName} ${user.lastName} (кнопки настроек профиля пользователя)</h5>

        <form action="selectUser" method="POST"><%--modelAttribute="user"--%>
            <select type="text" name="idSelectUser" ><%--multiple="true"--%>
                <option selected value ="${user.id}">(заданный)${user.firstName} ${user.lastName}</option>
                <%--<form:options items="${user_list}"  itemLabel="name" itemValue="id" />--%>
                <c:forEach items="${user_list}" var="tempUser">
                    <option value ="${tempUser.id}">${tempUser.firstName} ${tempUser.lastName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Войти на страницу" class="save" />
        </form>

        <h5>Oрганизация: ${user.organization.name}</h5>
        <form action="filterByNotificStatus"  method="post">
            Фильтры по словарю notificationStatus<Br>
            <c:forEach items="${notificStatus_list}" var="tempStatus">
                <c:choose>
                    <c:when test="${checked_list.contains(tempStatus)}">
                        <input type="checkbox" name="id" class= "checkbox" value="${tempStatus.id}" checked >${tempStatus.name}</input><Br>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="id" class= "checkbox" value="${tempStatus.id}">${tempStatus.name}</input><Br>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <button type="submit">Применить фильтр</button>
            <button type="submit" onclick="onCheck()">Отменить фильтр</button>
            <script>
                function onCheck() {
                    var mas = document.getElementsByClassName("checkbox");
                    for (var i=0;i<mas.length;i++){
                        mas[i].checked = true;//.setAttribute('checked', 'checked');
                    }
                }
            </script>
        </form>

        <form action="filterArchive" method="GET"><%--modelAttribute="user"--%>
            <Br>Показать архивные(эти уведомления наверн отмечается другим цветом) (checkbox) (я считаю что это:IF (статус=3,4,5,7 AND СрокПредоставленияОтвета=прошел) )<Br>
            <c:choose>
                <c:when test="${flagArchive}">
                    <input type="checkbox" name="flagArchive" value="true" checked >Показывать архивные</input><Br>
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="flagArchive" value="false">Показывать архивные</input><Br>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="mock" class="save" />
        </form>

        <p>фильтры по дате "dateResponse" относительного текущего времени и заданого</p>
        <br/>
        <p>Пагинация таблицы (общее кол-во найденных записей, + ссылки на другие страницы, + выбор по скольку записей за раз показывать)</p>
        <p>Регулируемая сортирвка над каждым полем</p>
        <p>Возможность отметить "галкой" записи (зачем не знаю)</p>
        <p>обычный столбец нумерации</p>
        <br/>
        <p>обычный Notification(с ограничениями выше)</p>
        <p>подсветка красным если "dateResponse" близка к текущей дате</p>
        <p>каким нибудь образом возможность нажать на уведомление чтоб можно было перейти на форму с её действиями(Action c выборкой по Notific_ID)(скорей всего двойной клик)</p>

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