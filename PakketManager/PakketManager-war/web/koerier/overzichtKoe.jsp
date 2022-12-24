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
                <c:forEach var="id" items="session.getParameter(bediendePakketten)">
                    <form method="<c:url value="bediendeOverzicht"/>">
                        <input type="hidden" name="ID" value="INSERT ID">
                        <input type="submit" value="INSERT ID">
                    </form>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>
