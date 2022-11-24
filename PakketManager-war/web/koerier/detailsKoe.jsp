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
        <h1>Bekijk uw pakket! (Koerier)</h1>
        <table>
            <tr>
                <th>Naam</th>
                <th>Adres</th>
                <th>Postcode</th>
                <th>Gemeente</th>
                <th>Gewicht</th>
                <th>Commentaar</th>
                <th>Status</th>
                <th>Probleem</th>
                <th>Geleverd</th>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <form>
                        <input type="submit" value="Probleem">
                    </form>
                </td>
                <td>
                    <form>
                        <input type="submit" value="Geleverd">
                    </form>
                </td>
            </tr>
        </table>
        <form>
            <input type="submit" value="Terug">
        </form>
    </body>
</html>
