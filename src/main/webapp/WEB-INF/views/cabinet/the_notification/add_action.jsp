<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Notification CRUD Application</title>
    <%--<link rel="stylesheet" href="/css/common.css" type="text/css" />--%>
    <style type="text/css">
        <%@ include file="/css/common.css" %>
    </style>
</head>
<body>

<jsp:include page="/templates/common_header_user.jsp" />

<div id="content">

    <div id="add_action">

        <form action="save"  method="post">

            <table class="table4input_data" style="width: auto">
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
                            window.location.href='${pageContext.request.contextPath}/cabinet/the_notification/actions'
                            "
            >
                ОТМЕНА
            </button>

        </form>
    </div>

</div>

<%--<div id="return">--%>
    <%--<a href="${pageContext.request.contextPath}/cabinet/the_notification/actions"> Back to "about the the_notification" </a>--%>
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