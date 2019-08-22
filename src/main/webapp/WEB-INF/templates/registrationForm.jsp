<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <%--<link rel="stylesheet" href="/css/common.css" type="text/css" />--%>
    <style type="text/css">
        <%@ include file="/css/common.css" %>
    </style>
</head>
<body>

<div id="registration">

    <h1>АСКД ЛКЗ</h1>
    <h4>Registration Form</h4>

    <%--@elvariable id="registrationDto" type="AddUserAccountForm"--%>
    <form:form  action="registration" method="POST" modelAttribute="registrationDto">

        <table class="table4input_data" style="width: auto;">
            <tr>
                <th>
                    <form:label path="firstName">firstName:</form:label>
                </th>
                <td>
                    <form:input type="test"
                                path="firstName" />
                    <Br>
                    <form:errors class="error" path="firstName"/>
                </td>
            </tr>
            <tr>
                <th>
                    <form:label path="lastName">lastName:</form:label>
                </th>
                <td>
                    <form:input type="test"
                                path="lastName" />
                    <Br>
                    <form:errors class="error" path="lastName"/>
                </td>
            </tr>
            <tr>
                <th>
                    <form:label path="organizationId">organization:</form:label>
                </th>
                <td>
                    <select type="text" name="organizationId" <%--onchange="this.form.submit()"--%> ><%--multiple="true"--%>
                        <c:forEach items="${listOrg}" var="tempOrg">
                            <option
                                <%--${tempOrg.status.id.equals(tempStatus.id) ? 'selected' : ''}--%>
                                    value ="${tempOrg.id}" label="${tempOrg.name}" />
                        </c:forEach>
                    </select>
                    <Br>
                    <form:errors class="error" path="organizationId"/>
                </td>
            </tr>
            <tr>
                <th>
                    <form:label path="username">username:</form:label>
                </th>
                <td>
                    <form:input type="test"
                                path="username" />
                    <Br>
                    <form:errors class="error" path="username"/>
                </td>
            </tr>
            <tr>
                <th>
                    <form:label path="password">password:</form:label>
                </th>
                <td>
                    <form:input type="password"
                                path="password" />
                    <Br>
                    <form:errors class="error" path="password"/>
                </td>
            </tr>
        </table>

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
                        window.location.href='${pageContext.request.contextPath}/'
                        "
        >
            ОТМЕНА
        </button>

    </form:form>

</div>

</body>
</html>