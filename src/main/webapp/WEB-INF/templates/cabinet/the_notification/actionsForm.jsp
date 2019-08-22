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

<jsp:include page="/fragments/common_header_user.jsp" />

<div id="content">


    <div id="common_info_about_notification">
        <h5 style="text-transform: uppercase;">
            ${currentNotification.notificationType}

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
                        <input type="text" name="MOCK_lastAction_Date" value="${render.getLatestAction().date}"
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
                        <input type="text" name="status.name" value="${currentNotification.status.name}"
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

                <form:form action="add_action/formPage" method="POST">
                    <%--<c:url var="addLink" value="add_action/formPage">--%>
                    <%--<c:param name="notificationId" value="${currentNotification.id}"/>--%>
                    <%--<c:param name="userId" value="${user.id}"/>--%>
                    <%--</c:url>--%>
                    <button type="button"  <%--type="form.submit"--%>
                            style="background-color: #4CAF50; color: white; display: inline-block;" <%--class="green_button"--%>
                            onclick="
                                    <%--window.location.href='${addLink}'--%>
                                    this.form.submit()
                                    "
                    >
                        Добавить
                    </button>
                </form:form>
            </div>

            <jsp:include page="/fragments/pagination.jsp" />

            <div id="list_actions">
                <table>
                    <tr>
                        <th>(Mock)
                            <Br>
                            <input type="checkbox">
                        </th>
                        <th>№</th>

                        <jsp:include page="/fragments/generHeadColumnWithOrder_ByEnum.jsp" />

                    </tr>

                    <c:forEach var="tempAction" items="${render.getPageImpl().getContent()}" varStatus="loopCount" >
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>${loopCount.count + render.getPageImpl().getNumber() * render.getPageImpl().getPageable().getPageSize() }
                            </td>

                            <td>${tempAction.actionType.name}</td>
                            <td>${tempAction.content}</td>
                            <td>${tempAction.date.toString()}</td>
                            <td>${tempAction.userByIdImplementor.firstName} ${tempAction.userByIdImplementor.lastName}</td>
                            <td>${tempAction.userByIdImplementor.organization.name}</td>
                            <td>${tempAction.statusAfterProcessing.name}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="return">
    <a href="${pageContext.request.contextPath}/cabinet/notifications"> Back to list </a>
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


    document.getElementById("orderFieldName=${render.state.orderByForm.orderFieldName}&desc=${render.state.orderByForm.desc}").classList.add('href-nav-item-current');
    //
    document.getElementById("radio-orderFieldName-orderFieldName=${render.state.orderByForm.orderFieldName}&desc=${render.state.orderByForm.desc}").setAttribute('checked', 'true');
    document.getElementById("radio-desc-orderFieldName=${render.state.orderByForm.orderFieldName}&desc=${render.state.orderByForm.desc}").setAttribute('checked', 'true');
    //
    //todo; trows Excpetion if element=null //JavaScript simple do not run next command line, ПОЭТОМУ this line is last
    document.getElementById("radioPageCount_${render.getPageImpl().getPageable().getPageNumber()}").setAttribute('checked', 'true');

</script>
</body>
</html>