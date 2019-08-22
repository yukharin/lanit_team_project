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
    <%--<link rel="stylesheet" href="<c:url value="/css/common.css" />" type="text/css" />--%>
    <style type="text/css">
        <%@ include file="/css/common.css" %>
    </style>

</head>
<body>

<jsp:include page="/fragments/common_header_user.jsp" />

<div id="content">

    <form action="filters"  method="post" <%--modelAttribute="cabinetDtoOnInput"--%> >
        <div id="filters">
            Фильтры:<Br>
            <table>
                <td style="text-align: left; width: 30%;">
                    <c:forEach items="${render.getStatuses4selectFilter()}" var="tempStatus">

                        <input ${render.getCheckedMainListNotificStatuses().contains(tempStatus) ? 'checked' : ''}
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
                    </c:forEach>
                </td>
                <td>
                    <input ${render.state.filterForm.showArchive ? 'checked' : ''}
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
                </td>
                <td>
                    <input type="checkbox">(Mock)Быстрый фильтр<Br>
                    <select type="text" name="selectFastFilter" ><%--multiple="true"--%>
                        <c:forEach items="${listFastFilter}" var="tempFilter">
                            <option
                                <%--${currentFastFilter.id.equals(tempFilter.id) ? 'selected' : ''}--%>
                                    value = "${tempFilter.id}">
                                    ${tempFilter.description}
                            </option>
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
    </form>

    <jsp:include page="/fragments/pagination.jsp" />

    (Mock-для чиновника возможность создать новое уведомление-тригер уже стоит)

    <div id="list_notifications">
        <table>
            <tr>
                <th>(Mock)
                    <Br><input type="checkbox"></th>
                <th>№</th>

                <jsp:include page="/fragments/generHeadColumnWithOrder_ByEnum.jsp" />

                <th>Действия</th>
            </tr>

            <form action="editStatus"  method="post" <%--modelAttribute="cabinetDtoOnInput"--%> >

                <c:forEach var="tempNotification" items="${render.getPageImpl().getContent()}" varStatus="loopCount" >

                    <tr>
                        <td><input type="checkbox"></td>
                            <%--<td>${notificLoopCount.count + (render.getPageImpl().getPageable().getPageNumber()-1)*render.getPageImpl().maxResult}--%>
                        <td>${loopCount.count + render.getPageImpl().getNumber() * render.getPageImpl().getPageable().getPageSize() }
                        </td>

                        <td>${tempNotification.notificationType}</td>
                        <td>${tempNotification.organization.name}</td>
                        <td>${tempNotification.dateResponse.toString()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.organization.government}">

                                    <input type="checkbox" class="hidden_input"
                                           id="idNotification4editStatus=${tempNotification.id}"
                                           name="selectedIdNotification4editStatus" value="${tempNotification.id}"></input>

                                    <input type="checkbox" class="hidden_input"
                                           id="idStatus_WithIdNotification=${tempNotification.id}"
                                           name="selectedNewIdStatus" value="${tempNotification.status.id}"></input>

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
                                            <c:choose>
                                                <c:when test="${tempNotification.status.id.equals(status.id)}">
                                                    <option selected
                                                            value ="${status.id}">${status.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value ="${status.id}">${status.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <%--<input type="submit" value="Применить изменения" class="register" />--%>
                                </c:when>
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
                                <%--<c:param name="userId" value="${user.id}"/>--%>
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

                                <%--<form action="deleteTheNotification"  method="post" >--%>
                                <%--<input type="hidden"--%>
                                <%--name="notificationId" value="${tempNotification.id}"></input>--%>
                                <c:url var="deleteLink" value="deleteTheNotification">
                                    <c:param name="notificationId" value="${tempNotification.id}"/>
                                </c:url>
                                <button type="button"<%--type="form.submit"--%>
                                        style="background-color: #af1b14; color: white; display: inline-block;" <%--class="green_button"--%>
                                        onclick="
                                                if (!(confirm('Are you sure?'))) return false
                                                // this.form.submit()
                                                window.location.href='${deleteLink}'
                                                "
                                >
                                    Delete
                                </button>
                            </c:if>
                        </td>
                    </tr>

                </c:forEach>

                <input id="flagNeedReplaceStatus"
                       type="hidden"
                       name="flagNeedReplaceStatus" value="false"></input>
            </form>

        </table>
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

    document.getElementById("orderFieldName=${render.state.orderByForm.getOrderFieldName()}&desc=${render.state.orderByForm.getDesc()}").classList.add('href-nav-item-current');
    //
    document.getElementById("radio-orderFieldName-orderFieldName=${render.state.orderByForm.getOrderFieldName()}&desc=${render.state.orderByForm.getDesc()}").setAttribute('checked', 'true');
    document.getElementById("radio-desc-orderFieldName=${render.state.orderByForm.getOrderFieldName()}&desc=${render.state.orderByForm.getDesc()}").setAttribute('checked', 'true');
    //
    //todo; trows Excpetion if element=null //JavaScript simple do not run next command line, ПОЭТОМУ this line is last
    document.getElementById("radioPageCount_${render.getPageImpl().getPageable().getPageNumber()}").setAttribute('checked', 'true');
</script>
</body>
</html>