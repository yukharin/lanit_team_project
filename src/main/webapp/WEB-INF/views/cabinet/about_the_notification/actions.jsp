<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<jsp:include page="/WEB-INF/templates/common_header_user.jsp" />

<div id="content">

    <form action="actions"  method="get" <%--modelAttribute="AboutTheNotificationDtoOnInput"--%> >

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
                            <input type="text" name="MOCK_lastAction_Date" value="${state.getLatestAction().date}"
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

                    <c:url var="addLink" value="addAction/formPage">
                        <c:param name="notificationId" value="${currentNotification.id}"/>
                        <c:param name="userId" value="${user.id}"/>
                    </c:url>
                    <button type="button"  <%--type="form.submit"--%>
                            style="background-color: #4CAF50; color: white; display: inline-block;" <%--class="green_button"--%>
                            onclick="
                                    window.location.href='${addLink}'
                                    "
                    >
                        Добавить
                    </button>
                </div>

                <jsp:include page="/WEB-INF/templates/pagination.jsp" />

                <div id="list_actions">
                    <table>
                        <tr>
                            <th>(Mock)
                                <Br><input type="checkbox"></th>
                            <th>№</th>
                            <th>Тип действия<%--tempAction.actionType.name--%>
                                <Br>
                                <span class ="radioDecorator radio-actionType">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=actionType&desc=true"
                                           name="orderFieldName" value="actionType"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=actionType&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=actionType&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=actionType&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=actionType&desc=true').setAttribute('checked', true);
                                    document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <span class ="radioDecorator radio-actionType">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=actionType&desc=false"
                                           name="orderFieldName" value="actionType"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=actionType&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=actionType&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=actionType&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=actionType&desc=false').setAttribute('checked', true);
                                document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Содержание<%--tempAction.content--%>
                                <Br>
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
                                    document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
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
                                document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Дата действия<%--tempAction.date.toString()--%>
                                <Br>
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
                                    document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
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
                                document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Исполнитель(can be need store IdUser)<%--tempAction.userByIdImplementor.firstName_lastName--%>
                                <Br>
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
                                    document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
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
                                document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>

                            </th>
                            <th>Подразделение<%--Id--%>
                                <Br>
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
                                    document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
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
                                document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                            </th>
                            <th>Статус после изменения<%--Id--%>
                                <Br>
                                <span class ="radioDecorator radio-statusAfterProcessing">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=statusAfterProcessing&desc=true"
                                           name="orderFieldName" value="statusAfterProcessing"
                                    <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=statusAfterProcessing&desc=true"
                                           name="desc" value="true"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=statusAfterProcessing&desc=true"
                                            onclick="
                                    document.getElementById('radio-orderFieldName-orderFieldName=statusAfterProcessing&desc=true').setAttribute('checked', true);
                                    document.getElementById('radio-desc-orderFieldName=statusAfterProcessing&desc=true').setAttribute('checked', true);
                                    document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                    this.form.submit()" >
                                        /\
                                    </button>
                                </span>
                                <Br>
                                <span class ="radioDecorator radio-statusAfterProcessing">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=statusAfterProcessing&desc=false"
                                           name="orderFieldName" value="statusAfterProcessing"
                                    <%--onchange="this.form.submit()"--%> >
                                    <input type="radio" id="radio-desc-orderFieldName=statusAfterProcessing&desc=false"
                                           name="desc" value="false"
                                    <%--onchange="this.form.submit()"--%> >
                                    <button type="button"  class="href-nav-item" id="orderFieldName=statusAfterProcessing&desc=false"
                                            onclick="
                                document.getElementById('radio-orderFieldName-orderFieldName=statusAfterProcessing&desc=false').setAttribute('checked', true);
                                document.getElementById('radio-desc-orderFieldName=statusAfterProcessing&desc=false').setAttribute('checked', true);
                                document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                this.form.submit()" >
                                        \/
                                    </button>
                                </span>
                        </tr>
                        <c:forEach var="tempAction" items="${state.getPageImpl().getContent()}" varStatus="loopCount" >
                            <tr>
                                <td><input type="checkbox"></td>
                                <%--<td>${actionLoopCount.count + (state.getPagination().currentPage-1)*state.getPagination().maxResult}</td>--%>
                                <td>
                                        ${loopCount.count + state.getPageImpl().getNumber() * state.getPageImpl().getPageable().getPageSize() }
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

        <input id="flagNeedSetFirstPage"
               type="hidden"
               name="flagNeedSetFirstPage" value="false"></input>
    </form>

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


    document.getElementById("orderFieldName=${state.getDto().getOrderFieldName()}&desc=${state.getDto().isDesc()}").classList.add('href-nav-item-current');
    //
    document.getElementById("radio-orderFieldName-orderFieldName=${state.getDto().getOrderFieldName()}&desc=${state.getDto().isDesc()}").setAttribute('checked', 'true');
    document.getElementById("radio-desc-orderFieldName=${state.getDto().getOrderFieldName()}&desc=${state.getDto().isDesc()}").setAttribute('checked', 'true');
    //
    //todo; trows Excpetion if element=null //JavaScript simple do not run next command line, ПОЭТОМУ this line is last
    document.getElementById("radioPageCount_${state.getPageImpl().getPageable().getPageNumber()}").setAttribute('checked', 'true');

</script>
</body>
</html>