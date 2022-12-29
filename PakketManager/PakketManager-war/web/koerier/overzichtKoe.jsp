<%-- 
    Document   : overzicht
    Created on : 19-nov-2022, 14:48:28
    Author     : vmmau
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <tr>
                <c:forEach var="pakket" items="${applicationScope.koerierPakketten}">
                    <form method=post action="<c:url value="GenericHandler"/>">
                        <td>${pakket.getId()}</td>
                        <td><input type="submit" value="Bekijk"></td>
                        <input type="hidden" name="hidden" value="overzichtKoerierBekijk">
                        <input type="hidden" name="pakketID" value="${pakket.getId()}">
                    </form>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>
