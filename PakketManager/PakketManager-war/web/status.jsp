<%-- 
    Document   : status
    Created on : 19-nov-2022, 14:44:12
    Author     : vmmau
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pakketverdeling</title>
    </head>
    <body>
        <h1>Uw pakket werd opgezocht</h1>
        <table>
            <tr>
                <th>pnr</th>
                <th>status</th>
            </tr>
            <tr>
                <td>${pakketID}</td>
                <td>${pakketStatus}</td>
            </tr>
        </table>
        <form method=post action="<c:url value="GenericHandler"/>">
            <input type="submit" value="Terug">
            <input type="hidden" name="hidden" value="statusGlobalTerug">
        </form>
    </body>
</html>