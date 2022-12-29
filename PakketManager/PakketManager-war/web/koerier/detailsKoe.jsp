<%-- 
    Document   : details
    Created on : 19-nov-2022, 14:49:42
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
        <h1>Bekijk uw pakket ${pakket.getPnr()}! (Koerier)</h1>
        <table>
            <tr>
                <th>Naam</th>
                <th>Straat</th>
                <th>Nummer</th>
                <th>Postcode</th>
                <th>Gemeente</th>
                <th>Gewicht</th>
                <th>Commentaar</th>
                <th>Status</th>
                <th>Probleem</th>
                <th>Geleverd</th>
            </tr>
            <tr>
                <td>${pakket.getLnaam()}</td>
                <td>${pakket.getLstraat()}</td>
                <td>${pakket.getLnummer()}</td>
                <td>${pakket.getLpostcode()}</td>
                <td>${pakket.getLgemeente()}</td>
                <td>${pakket.getPgewicht()}</td>
                <td>${pakket.getPcommentaar()}</td>
                <td>${pakket.getPstatus()}</td>
                <td>
                    <form method=post action="<c:url value="GenericHandler"/>">
                        <input type="submit" value="Probleem">
                        <input type="hidden" name="hidden" value="detailsKoerierProbleem">
                        <input type="hidden" name="pakketID" value="${pakket.getPnr()}">
                    </form>
                </td>
                <td>
                    <form method=post action="<c:url value="GenericHandler"/>">
                        <input type="submit" value="Geleverd">
                        <input type="hidden" name="hidden" value="detailsKoerierGeleverd">
                        <input type="hidden" name="pakketID" value="${request.pakket.getPnr()}">
                    </form>
                </td>
            </tr>
        </table>
        <form method=post action="<c:url value="GenericHandler"/>">
            <input type="submit" value="Terug">
            <input type="hidden" name="hidden" value="detailsKoerierTerug">
        </form>
    </body>
</html>
