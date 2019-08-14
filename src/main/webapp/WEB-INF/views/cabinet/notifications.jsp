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

    <form action="filters"  method="post" <%--modelAttribute="cabinetDtoOnInput"--%> >
        <div id="filters">
            <%--<form action="filterByNotificStatus"  method="get">--%>
            Фильтры:<Br>
            <table>
                <td style="text-align: left; width: 30%;">
                    <c:forEach items="${render.getStatuses4selectFilter()}" var="tempStatus">
                        <c:choose>
                            <c:when test="${render.getCheckedMainListNotificStatuses().contains(tempStatus)}">
                                <input checked
                                       class= "checkboxStatuses4select"
                                       type="checkbox"
                                       name="idFilterStatus"  value="${tempStatus.id}"
                                       onchange="
                                       // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                       this.form.submit()"
                                >
                                        ${tempStatus.name}
                                </input>
                                <Br>
                            </c:when>
                            <c:otherwise>
                                <input class= "checkboxStatuses4select"
                                       type="checkbox"
                                       name="idFilterStatus" value="${tempStatus.id}"
                                       onchange="
                                       // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                       this.form.submit()"
                                >
                                        ${tempStatus.name}
                                </input>
                                <Br>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${render.state.filterDto.getShowArchive()}">
                            <input checked
                                   class= "checkboxshowArchive"
                                   type="checkbox"
                                   name="showArchive" value="true"
                                   onchange="
                                   // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                   this.form.submit()"
                            >
                            Показывать архивные уведомления
                            </input>
                            <Br>
                        </c:when>
                        <c:otherwise>
                            <input class= "checkboxshowArchive"
                                   type="checkbox"
                                   name="showArchive" value="true"
                                   onchange="
                                   // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                   this.form.submit()"
                            >
                            Показывать архивные уведомления
                            </input>
                            <Br>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <input type="checkbox">(Mock)Быстрый фильтр<Br>
                    <select type="text" name="selectFastFilter" ><%--multiple="true"--%>
                        <c:forEach items="${listFastFilter}" var="tempFilter">
                            <%--<c:choose>--%>
                            <%--<c:when test="${currentFastFilter.id.equals(tempFilter.id)}">--%>
                            <%--<option &lt;%&ndash;selected&ndash;%&gt;--%>
                            <%--value = "${tempFilter.id}">--%>
                            <%--${tempFilter.description}--%>
                            <%--</option>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <option value = "${tempFilter.id}">
                                    ${tempFilter.description}
                            </option>
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        </c:forEach>
                    </select>
                    ///
                </td>
                <td>
                    (Mock)Кнопка «Показать дополнительные фильтры»
                </td>
            </table>
            <button type="button"<%--type="form.submit"--%>
                    style="background-color: #4CAF50; color: white; display: inline-block;"<%--class="green_button"--%>
                    onclick="
            // document.getElementById('flagNeedSetFirstPage').setAttribute('value', 'true');
            this.form.submit();"
            >
                Применить фильтр
            </button>
            <button type="button"<%--type="form.submit"--%> style="background-color: #666666; color: white; display: inline-block;"
                    onclick="
            filterButtonUndoInDefault4setChecked();
            // document.getElementById('flagNeedSetFirstPage').setAttribute('value', 'true');
            this.form.submit();"
            >
                Отменить фильтр
            </button>
            <%--</form>--%>
        </div>

        <%--<input id="flagNeedSetFirstPage"--%>
        <%--type="hidden"--%>
        <%--name="flagNeedSetFirstPage" value="false"></input>--%>
    </form>

    <jsp:include page="/templates/pagination.jsp" />

    <div id="list_notifications">
        <table>
            <form action="orderby"  method="post" <%--modelAttribute="cabinetDtoOnInput"--%> >

                <tr>
                    <th>(Mock)
                        <Br><input type="checkbox"></th>
                    <th>№</th>
                    <th>Вид уведомления<%--notificationType--%>
                        <Br>
                        <span class ="radioDecorator radio-notificationType">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=notificationType&desc=true"
                                   name="orderFieldName" value="notificationType"
                            <%--onchange="this.form.submit()" --%> >
                            <input type="radio" id="radio-desc-orderFieldName=notificationType&desc=true"
                                   name="desc" value="true"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=notificationType&desc=true"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=notificationType&desc=true').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=notificationType&desc=true').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                /\
                            </button>
                        </span>
                        <Br>
                        <span class ="radioDecorator radio-notificationType">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=notificationType&desc=false"
                                   name="orderFieldName" value="notificationType"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=notificationType&desc=false"
                                   name="desc" value="false"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=notificationType&desc=false"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=notificationType&desc=false').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=notificationType&desc=false').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                \/
                            </button>
                        </span>
                    </th>
                    <th>Заказчик<%--organization.NAME--%>
                        <Br>
                        <span class ="radioDecorator radio-organization.name">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=organization.name&desc=true"
                                   name="orderFieldName" value="organization.name"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=organization.name&desc=true"
                                   name="desc" value="true"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=organization.name&desc=true"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=organization.name&desc=true').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=organization.name&desc=true').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                /\
                            </button>
                        </span>
                        <Br>
                        <span class ="radioDecorator radio-organization.name">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=organization.name&desc=false"
                                   name="orderFieldName" value="organization.name"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=organization.name&desc=false"
                                   name="desc" value="false"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=organization.name&desc=false"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=organization.name&desc=false').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=organization.name&desc=false').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                \/
                            </button>
                        </span>
                    </th>
                    <th>Срок предоставления ответа<%--dateResponse--%>
                        <Br>
                        <span class ="radioDecorator radio-dateResponse">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=dateResponse&desc=true"
                                   name="orderFieldName" value="dateResponse"
                            <%--onchange="this.form.submit()" --%> >
                            <input type="radio" id="radio-desc-orderFieldName=dateResponse&desc=true"
                                   name="desc" value="true"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=dateResponse&desc=true"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=dateResponse&desc=true').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=dateResponse&desc=true').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                /\
                            </button>
                        </span>
                        <Br>
                        <span class ="radioDecorator radio-dateResponse">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=dateResponse&desc=false"
                                   name="orderFieldName" value="dateResponse"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=dateResponse&desc=false"
                                   name="desc" value="false"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=dateResponse&desc=false"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=dateResponse&desc=false').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=dateResponse&desc=false').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                \/
                            </button>
                        </span>
                    </th>
                    <th>Статус обработки уведомления<%--status--%>
                        <Br>
                        <span class ="radioDecorator radio-status">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=status&desc=true"
                                   name="orderFieldName" value="status"
                            <%--onchange="this.form.submit()" --%> >
                            <input type="radio" id="radio-desc-orderFieldName=status&desc=true"
                                   name="desc" value="true"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=status&desc=true"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=status&desc=true').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=status&desc=true').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                /\
                            </button>
                        </span>
                        <Br>
                        <span class ="radioDecorator radio-status">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=status&desc=false"
                                   name="orderFieldName" value="status"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=status&desc=false"
                                   name="desc" value="false"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=status&desc=false"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=status&desc=false').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=status&desc=false').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                \/
                            </button>
                        </span>
                    </th>
                    <th>Номер уведомления<%--Id--%>
                        <Br>
                        <span class ="radioDecorator radio-id">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=id&desc=true"
                                   name="orderFieldName" value="id"
                            <%--onchange="this.form.submit()" --%> >
                            <input type="radio" id="radio-desc-orderFieldName=id&desc=true"
                                   name="desc" value="true"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=id&desc=true"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=id&desc=true').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=id&desc=true').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                /\
                            </button>
                        </span>
                        <Br>
                        <span class ="radioDecorator radio-id">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=id&desc=false"
                                   name="orderFieldName" value="id"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=id&desc=false"
                                   name="desc" value="false"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=id&desc=false"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=id&desc=false').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=id&desc=false').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                \/
                            </button>
                        </span>
                    </th>
                    <th>Дата получения уведомления<%--dateReceived--%>
                        <Br>
                        <span class ="radioDecorator radio-dateReceived">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=dateReceived&desc=true"
                                   name="orderFieldName" value="dateReceived"
                            <%--onchange="this.form.submit()" --%> >
                            <input type="radio" id="radio-desc-orderFieldName=dateReceived&desc=true"
                                   name="desc" value="true"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=dateReceived&desc=true"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=dateReceived&desc=true').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=dateReceived&desc=true').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                /\
                            </button>
                        </span>
                        <Br>
                        <span class ="radioDecorator radio-dateReceived">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=dateReceived&desc=false"
                                   name="orderFieldName" value="dateReceived"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=dateReceived&desc=false"
                                   name="desc" value="false"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=dateReceived&desc=false"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=dateReceived&desc=false').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=dateReceived&desc=false').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                \/
                            </button>
                        </span>
                    </th>
                    <th>Номер письма<%--letterNumber--%>
                        <Br>
                        <span class ="radioDecorator radio-letterNumber">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=letterNumber&desc=true"
                                   name="orderFieldName" value="letterNumber"
                            <%--onchange="this.form.submit()" --%> >
                            <input type="radio" id="radio-desc-orderFieldName=letterNumber&desc=true"
                                   name="desc" value="true"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=letterNumber&desc=true"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=letterNumber&desc=true').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=letterNumber&desc=true').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                /\
                            </button>
                        </span>
                        <Br>
                        <span class ="radioDecorator radio-letterNumber">
                            <input type="radio" id="radio-orderFieldName-orderFieldName=letterNumber&desc=false"
                                   name="orderFieldName" value="letterNumber"
                            <%--onchange="this.form.submit()"--%> >
                            <input type="radio" id="radio-desc-orderFieldName=letterNumber&desc=false"
                                   name="desc" value="false"
                            <%--onchange="this.form.submit()"--%> >
                            <button type="button" class="href-nav-item" id="orderFieldName=letterNumber&desc=false"
                                    onclick="
        document.getElementById('radio-orderFieldName-orderFieldName=letterNumber&desc=false').setAttribute('checked', true);
        document.getElementById('radio-desc-orderFieldName=letterNumber&desc=false').setAttribute('checked', true);
        // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
        this.form.submit()" >
                                \/
                            </button>
                        </span>
                    </th>
                    <th>Действия</th>
                </tr>

            </form>

            <form action="editStatus"  method="post" <%--modelAttribute="cabinetDtoOnInput"--%> >

                <c:forEach var="tempNotification" items="${render.getPageImpl().getContent()}" varStatus="loopCount" >
                    <tr>
                        <td><input type="checkbox"></td>
                            <%--<td>${notificLoopCount.count + (render.getPageImpl().getPageable().getPageNumber()-1)*render.getPageImpl().maxResult}--%>
                        <td>
                                ${loopCount.count + render.getPageImpl().getNumber() * render.getPageImpl().getPageable().getPageSize() }
                        </td>
                        <td>${tempNotification.notificationType}</td>
                        <td>${tempNotification.organization.name}</td>
                        <td>${tempNotification.dateResponse.toString()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.organization.government}">
                                    <%--<form action="editStatus" method="get">--%>
                                    <input type="checkbox" class="hidden_input"
                                           id="idNotification4editStatus=${tempNotification.id}"
                                           name="selectedIdNotification4editStatus" value="${tempNotification.id}"></input>
                                    <%----%>
                                    <input type="checkbox" class="hidden_input"
                                           id="idStatus_WithIdNotification=${tempNotification.id}"
                                           name="selectedNewIdStatus" value="${tempNotification.status.id}"></input>
                                    <%----%>
                                    <select type="text" id="selectElement_NewActionId_WithIdNotification=${tempNotification.id}"<%--name="idStatus_With_idNotification=${tempNotification.id}"--%>
                                            onchange="
                                                    document.getElementById('flagNeedReplaceStatus').setAttribute('value', 'true');
                                                    //
                                                    document.getElementById('idNotification4editStatus=${tempNotification.id}').setAttribute('checked', 'true');
                                                    //
                                                    var selectElement = document.getElementById('selectElement_NewActionId_WithIdNotification=${tempNotification.id}');
                                                    var n = selectElement.options.selectedIndex;
                                                    var optionValue = selectElement.options[n].value;
                                                    //
                                                    var status = document.getElementById('idStatus_WithIdNotification=${tempNotification.id}');
                                                    // status.value = selectElement.options[n].value;
                                                    status.setAttribute('value', optionValue);
                                                    status.setAttribute('checked', 'true');
                                                    //
                                                    this.form.submit()" >
                                        <c:forEach items="${render.getStatuses4selectFilter()}" var="status">
                                            <%----%>
                                            <c:choose>
                                                <c:when test="${tempNotification.status.id.equals(status.id)}">
                                                    <option selected
                                                            value ="${status.id}">${status.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value ="${status.id}">${status.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <%----%>
                                        </c:forEach>
                                    </select>
                                    <%--<input type="submit" value="Применить изменения" class="save" />--%>
                                    <%--</form>--%>
                                </c:when>
                                <%----%>
                                <c:otherwise>
                                    ${tempNotification.status.name}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${tempNotification.id}</td>
                        <td>${tempNotification.dateReceived.toString()}</td>
                        <td>${tempNotification.letterNumber}</td>
                        <td>
                            Кол-во: ${tempNotification.actions.size()}
                            <Br>
                            <c:url var="moreLink" value="the_notification/selectTheNotification">
                                <c:param name="notificationId" value="${tempNotification.id}"/>
                                <c:param name="userId" value="${user.id}"/>
                            </c:url>
                            <button type="button"<%--type="form.submit"--%>
                                    style="background-color: #4CAF50; color: white; display: inline-block;" <%--class="green_button"--%>
                                    onclick="
                                            window.location.href='${moreLink}'
                                            "
                            >
                                Подробнее
                            </button>
                            <c:if test="${user.organization.government}">
                                <c:url var="deleteLink" value="deleteTheNotification">
                                    <c:param name="notificationId" value="${tempNotification.id}"/>
                                    <%--<c:param name="userId" value="${user.id}"/>--%>
                                </c:url>
                                <button type="button"<%--type="form.submit"--%>
                                        style="background-color: #af1b14; color: white; display: inline-block;" <%--class="green_button"--%>
                                        onclick="
                                                if (!(confirm('Are you sure?'))) return false
                                                window.location.href='${deleteLink}'
                                                "
                                >
                                    Delete
                                </button>
                            </c:if>
                            <Br>
                        </td>
                    </tr>
                </c:forEach>

                <input id="flagNeedReplaceStatus"
                       type="hidden"
                       name="flagNeedReplaceStatus" value="false"></input>
            </form>

        </table>
        (Mock-для чиновника возможность создать уведомление-тригер уже стоит)
    </div>

</div>

<script>
    function filterButtonUndoInDefault4setChecked() {
        var mas = document.getElementsByClassName("checkboxStatuses4select");
        for (var i=0;i<mas.length;i++){
            mas[i].checked = true;//.setAttribute('checked', 'checked');
        }
        var mas = document.getElementsByClassName("checkboxshowArchive");
        for (var i=0;i<mas.length;i++){
            mas[i].checked = false;//.setAttribute('checked', 'checked');
        }
    }

    document.getElementById("orderFieldName=${render.state.orderByDto.getOrderFieldName()}&desc=${render.state.orderByDto.getDesc()}").classList.add('href-nav-item-current');
    //
    document.getElementById("radio-orderFieldName-orderFieldName=${render.state.orderByDto.getOrderFieldName()}&desc=${render.state.orderByDto.getDesc()}").setAttribute('checked', 'true');
    document.getElementById("radio-desc-orderFieldName=${render.state.orderByDto.getOrderFieldName()}&desc=${render.state.orderByDto.getDesc()}").setAttribute('checked', 'true');
    //
    //todo; trows Excpetion if element=null //JavaScript simple do not run next command line, ПОЭТОМУ this line is last
    document.getElementById("radioPageCount_${render.getPageImpl().getPageable().getPageNumber()}").setAttribute('checked', 'true');
</script>
</body>
</html>