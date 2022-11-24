<%-- 
    Document   : details
    Created on : 19-nov-2022, 14:49:36
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
        <h1>Bekijk uw pakket! (Bediendes)</h1>
        <form method=post action="<c:url value="GenericHandler"/>">
            <table>
                <tr>
                    <th>Naam</th>
                    <th>Adres</th>
                    <th>Postcode</th>
                    <th>Gemeente</th>
                    <th>Gewicht</th>
                    <th>Commentaar</th>
                    <th>Koerier</th>
                </tr>
                <tr>
                    <td>${sessionScope.naam}</td>
                    <td>${sessionScope.adres}</td>
                    <td>${sessionScope.postcode}</td>
                    <td>${sessionScope.gemeente}</td>
                    <td>${sessionScope.gewicht}</td>
                    <td>${sessionScope.commentaar}</td>
                    <td>${sessionScope.koerier}</td>
                </tr>
            </table>
            <input type="submit" value="Terug">
            <input type="hidden" name="hidden" value="detailsBediendeTerug">
        </form>
    </body>
</html>
