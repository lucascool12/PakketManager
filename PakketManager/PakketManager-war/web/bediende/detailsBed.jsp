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
        <form method=post action="<c:url value="/GenericHandler"/>">
            <table>
                <tr>
                    <th>Naam</th>
                    <th>Straat</th>
                    <th>Nummer</th>
                    <th>Postcode</th>
                    <th>Gemeente</th>
                    <th>Gewicht</th>
                    <th>Commentaar</th>
                    <th>Koerier</th>
                </tr>
                <tr>
                    <td>${pakket.getLnaam()}</td>
                    <td>${pakket.getLstraat()}</td>
                    <td>${pakket.getLnummer()}</td>
                    <td>${pakket.getLpostcode()}</td>
                    <td>${pakket.getLgemeente()}</td>
                    <td>${pakket.getPgewicht()}</td>
                    <td>${pakket.getPcommentaar()}</td>
                    <td>${koerier.getKnaam().getGebruikersnaam()}</td>
                </tr>
            </table>
            <input type="submit" value="Terug">
            <input type="hidden" name="hidden" value="detailsBediendeTerug">
        </form>
        <p>Log uit</p>
        <form method=post action="<c:url value="/GenericHandler"/>">
            <input type="submit" value="Log uit">
            <input type="hidden" name="hidden" value="logUit">
        </form>
    </body>
</html>
