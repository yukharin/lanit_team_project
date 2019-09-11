<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"--%>
<%--"http://www.w3.org/TR/html4/loose.dtd">--%>

<%--//html//head--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

<form action="orderby"  method="post" <%--modelAttribute="cabinetDtoOnInput"--%> >

    <c:forEach var="tempColumn" items="${columnTable}" varStatus="loopCount" >
        <th>${tempColumn.getDescription()}
            <c:forEach var="descCounter" begin="0" end="1">
                <Br>
                <span class ="radioDecorator radio-${tempColumn.getVarName()}">
                                    <input type="radio" id="radio-orderFieldName-orderFieldName=${tempColumn.getVarName()}&desc=${descCounter==0}"
                                           name="orderFieldName" value="${tempColumn.getVarName()}"
                                        <%--onchange="this.form.submit()" --%> >
                                    <input type="radio" id="radio-desc-orderFieldName=${tempColumn.getVarName()}&desc=${descCounter==0}"
                                           name="desc" value="${descCounter==0}"
                                        <%--onchange="this.form.submit()"--%> >
                                    <button type="button" class="href-nav-item" id="orderFieldName=${tempColumn.getVarName()}&desc=${descCounter==0}"
                                            onclick="
                                                    document.getElementById('radio-orderFieldName-orderFieldName=${tempColumn.getVarName()}&desc=${descCounter==0}').setAttribute('checked', 'true');
                                                    document.getElementById('radio-desc-orderFieldName=${tempColumn.getVarName()}&desc=${descCounter==0}').setAttribute('checked', 'true');
                                                    // document.getElementById('flagNeedSetFirstPage').setAttribute('value', true);
                                                    this.form.submit()" >
                                            ${descCounter==0 ? '/\\' : '\\/'}
                                    </button>
                                </span>
            </c:forEach>
        </th>
    </c:forEach>

</form>