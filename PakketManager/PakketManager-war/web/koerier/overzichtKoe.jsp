<%-- 
    Document   : overzicht
    Created on : 19-nov-2022, 14:48:28
    Author     : vmmau
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="st" class="beans.StatusTranslater"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welkom koerier!</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Status</th>
            </tr>
            <c:forEach var="pakket" items="${sessionScope.koerierPakketten}">
                <c:choose>
                    <c:when test="${(curDate.getTime() - pakket.getBesteldatum().getTime()) > 48*3600*1000}">
                        <tr style="font-weight: bold; color: red;">
                    </c:when>
                    <c:otherwise>
                        <tr>
                    </c:otherwise>
                </c:choose>
                <form method=post action="<c:url value="GenericHandler"/>">
                    <td>${pakket.getPnr()}</td>
                    <td>${st.getStatusNaam(pakket.getPstatus())}</td>
                    <td><input type="submit" value="Bekijk"></td>
                    <input type="hidden" name="hidden" value="overzichtKoerierBekijk">
                    <input type="hidden" name="pakketID" value="${pakket.getPnr()}">
                </form>
                </tr>
            </c:forEach>
        </table>
        <p>Log uit</p>
        <form method=post action="<c:url value="/GenericHandler"/>">
            <input type="submit" value="Log uit">
            <input type="hidden" name="hidden" value="logUit">
        </form>
    </body>
</html>
