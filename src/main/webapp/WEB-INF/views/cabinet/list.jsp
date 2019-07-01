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
        <h5>пользователь пока админ</h5>
        <h5>(кнопки настроек профиля пользователя)</h5>
        <h5>название 1-ой организация пользователя(пока его нет)</h5>
            <br/>
        <p>фильтры по словарю notificationStatus</p>
        <p>фильтры архивности (gj прошедшим датам, или уже статус уведомления=выполненый)</p>
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
                <th>notificOrg.NAME</th>
                <th>notificationType</th>
                <th>dateReceived</th>
                <th>dateResponse</th>
                <th>letterNumber</th>
                <th>userByIdUserCuratorGos</th>
                <th>userByIdUserImplementor</th>
                <th>notificationStatus</th>
                <th>Actions</th>
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
                    <td>${tempNotification.notificOrg.name}</td>
                    <td>${tempNotification.notificationType}</td>
                    <td>${tempNotification.dateReceived.toString()}</td>
                    <td>${tempNotification.dateResponse.toString()}</td>
                    <td>${tempNotification.letterNumber}</td>
                    <td>${tempNotification.userByIdUserCuratorGos}</td>
                    <td>${tempNotification.userByIdUserImplementor}</td>
                    <td>${tempNotification.notificationStatus}</td>
                    <td>${tempNotification.actions}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) return false">Delete</a>
                        |
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>