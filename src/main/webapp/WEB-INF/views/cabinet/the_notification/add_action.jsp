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

        <%--@elvariable id="actionPortionForm" type="ActionPortionForm"--%>
        <form:form  action="save" method="POST" modelAttribute="actionPortionForm"> <%--modelAttribute="actionPortionForm"--%>

            <form:label path="notificationId"></form:label>
            <form:input type="hidden"
                        path="notificationId" value="${currentNotification.id}"></form:input>
            <form:errors path="notificationId"/>

            <table class="table4input_data" style="width: auto">
                <tr>
                    <th>
                        <form:label path="idUserImplementor">Ответственный исполнитель заказчика:</form:label>
                    </th>
                    <td>
                        <form:select type="text" path="idUserImplementor"> <%--onchange="this.form.submit()"--%><%--multiple="true"--%>
                            <c:forEach items="${currentNotification.organization.users}" var="tempUser">
                                <option ${user.id.equals(tempUser.id) ? 'selected' : ''}
                                        value ="${tempUser.id}" label="${tempUser.firstName} ${tempUser.lastName}" />
                            </c:forEach>
                        </form:select>

                        <form:errors path="idUserImplementor"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <form:label path="idActionType">Действие:</form:label>
                    </th>
                    <td>
                        <form:select type="text" path="idActionType"> <%--onchange="this.form.submit()"--%><%--multiple="true"--%>
                            <c:forEach items="${listActionType}" var="tempActionType">
                                <option value ="${tempActionType.id}" label="${tempActionType.name}" />
                                <%--selected="${user.id.equals(tempUser.id)}--%>
                            </c:forEach>
                        </form:select>

                        <form:errors path="idActionType"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <form:label path="idNotificationStatus">Статус:</form:label>
                    </th>
                    <td>
                        <select type="text" name="idNotificationStatus" <%--onchange="this.form.submit()"--%> ><%--multiple="true"--%>
                            <c:forEach items="${listStatus}" var="tempStatus">
                                <option ${currentNotification.status.id.equals(tempStatus.id) ? 'selected' : ''}
                                        value ="${tempStatus.id}" label="${tempStatus.name}" />
                            </c:forEach>
                        </select>

                        <form:errors path="idNotificationStatus"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <form:label path="content">Комментарий:</form:label>
                    </th>
                    <td>
                        <form:textarea id="comment" path="content" maxlength="300"
                                       rows="4" cols="50" />

                        <form:errors path="content"/>
                        <Br>
                    </td>
                </tr>
            </table>

            <Br>
            <%--${bindingResult.getFieldError("content").getDefaultMessage()}--%>
            <Br>
            <button type="button"<%--type="form.submit"--%>
                    style="background-color: #4CAF50; color: white; display: inline-block;" <%--class="green_button"--%>
                    onclick="
                    // if(document.getElementById('comment').value == '') {
                    //     alert('поле - Комментарий пустое');
                    // }else {
                        this.form.submit();
                    // }
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

        </form:form>
    </div>

</div>

</body>
</html>