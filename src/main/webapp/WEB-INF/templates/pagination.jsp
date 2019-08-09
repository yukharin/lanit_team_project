<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"--%>
<%--"http://www.w3.org/TR/html4/loose.dtd">--%>

<%--//html//head--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

<div id="state.PageImpl">
    <table>
        <td style="text-align: left">
            Найдено записей: ${state.getPageImpl().getTotalElements()}<Br>
        </td>
        <td style="text-align: center">
            <div id="page-navigator">
                <c:if test="${state.getPageImpl().getTotalPages() > 1}">

                            <span class ="radioDecorator">
                                <input id="radioPageFirst"
                                       type="radio"
                                       name="page" value="0">
                                <button type="button" class="href-nav-item"
                                        onclick="
        document.getElementById('radioPageFirst').setAttribute('checked', 'true');
        this.form.submit()" >
                                    <<
                                </button>
                            </span>

                    <span class ="radioDecorator">
                                <input id="radioPagePrev"
                                       type="radio"
                                       name="page" value="${(state.getPageImpl().getPageable().getPageNumber() != 0) ? state.getPageImpl().getPageable().getPageNumber()-1 : 0 }">
                                <button type="button" class="href-nav-item"
                                        onclick="
        document.getElementById('radioPagePrev').setAttribute('checked', 'true');
        this.form.submit()">
                                    <-
                                </button>
                            </span>

                    <%--<c:forEach var="tempNotification" items="${state.getPageImpl().getContent()}" varStatus="notificLoopCount" >--%>
                    <%--<c:forEach items="${state.getPageImpl().getTotalPages()}" var = "page" varStatus="pageLoopCount" >--%>
                    <c:forEach var="page" begin="0" end="${state.getPageImpl().getTotalPages()-1}">
                                <span class ="radioDecorator">
                                <input id="radioPageCount_${page}"
                                       type="radio"
                                       name="page" value="${page}">
                                <button type="button" class="href-nav-item"
                                        onclick="
                                                document.getElementById('radioPageCount_${page}').setAttribute('checked', 'true');
                                                this.form.submit()" >
                                        ${page+1}
                                </button>
                                </span>
                    </c:forEach>

                    <%--<c:forEach items="${state.getNavigationPages()}" var = "page">--%>
                    <%--<c:choose>--%>
                    <%--<c:when test="${page != -1 }">--%>
                    <%--<c:if test="${page == state.getPageImpl().getPageable().getPageNumber()}">--%>
                    <%----%>
                    <%--<span class ="radioDecorator">--%>
                    <%--<input id="radioPageCount_${page}"--%>
                    <%--type="radio"--%>
                    <%--name="page" value="${page}">--%>
                    <%--<button type="button" class="href-nav-item-current"--%>
                    <%--onclick="--%>
                    <%--document.getElementById('radioPageCount_${page}').setAttribute('checked', true);--%>
                    <%--this.form.submit()">--%>
                    <%--${page}--%>
                    <%--</button>--%>
                    <%--</span>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${page != state.getPageImpl().getPageable().getPageNumber()}">--%>
                    <%----%>
                    <%--<span class ="radioDecorator">--%>
                    <%--<input id="radioPageCount_${page}"--%>
                    <%--type="radio"--%>
                    <%--name="page" value="${page}">--%>
                    <%--<button type="button" class="href-nav-item"--%>
                    <%--onclick="--%>
                    <%--document.getElementById('radioPageCount_${page}').setAttribute('checked', true);--%>
                    <%--this.form.submit()" >--%>
                    <%--${page}--%>
                    <%--</button>--%>
                    <%--</span>--%>
                    <%----%>
                    <%--</c:if>--%>
                    <%----%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                    <%--<button type="button"  class="href-nav-item"> ... </button>--%>
                    <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                    <%----%>
                    <%--</c:forEach>--%>

                    <span class ="radioDecorator">
                                <input id="radioPageNext"
                                       type="radio"
                                       name="page"
                                       value="${(state.getPageImpl().getPageable().getPageNumber() != state.getPageImpl().getTotalPages()-1)
                                       ? state.getPageImpl().getPageable().getPageNumber()+1
                                       : state.getPageImpl().getPageable().getPageNumber() }">
                                <button type="button" class="href-nav-item"
                                        onclick="
                                document.getElementById('radioPageNext').setAttribute('checked', 'true');
                                this.form.submit()" >
                                ->
                                </button>
                                </span>
                    <span class ="radioDecorator">
                                <input id="radioPageLast"
                                       type="radio"
                                       name="page" value="${state.getPageImpl().getTotalPages()-1}">
                                <button type="button" class="href-nav-item"
                                        onclick="
                                document.getElementById('radioPageLast').setAttribute('checked', 'true');
                                this.form.submit()" >
                                >>
                                </button>
                                </span>

                </c:if>
            </div>
        </td>
        <td style="text-align: right">
            <div id="selectMaxResult">
                Показывать по :
                <select type="text" name="maxResult"
                        onchange="
        document.getElementById('flagNeedSetFirstPage').setAttribute('value', 'true');
        this.form.submit()"
                ><%--multiple="true"--%>
                    <c:forEach items="${selectShowListMaxResult}" var="tempMaxResult">
                        <c:choose>
                            <c:when test="${tempMaxResult.equals(state.getPageImpl().getPageable().getPageSize())}">
                                <option selected
                                        value ="${tempMaxResult}">${tempMaxResult}</option>
                            </c:when>
                            <c:otherwise>
                                <option value ="${tempMaxResult}">${tempMaxResult}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <%--<input type="submit" value="Войти на страницу" class="save" />--%>
            </div>
        </td>
    </table>
</div>