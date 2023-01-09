<%-- 
    Document   : overzicht
    Created on : 19-nov-2022, 14:48:21
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
        <h1>Welkom bediende!</h1>
        <p>Zoek een pakket op:</p>
        <table>
            <tr>
                <th>ID</th>
                <th>Status</th>
                <th></th>
            </tr>
            <c:forEach var="pakket" items="${sessionScope.bediendePakketten}">
                <tr>
                <form method=post action="<c:url value="/GenericHandler"/>">
                    <td>${pakket.getPnr()}</td>
                    <td>${st.getStatusNaam(pakket.getPstatus())}</td>
                    <td><input type="submit" value="Bekijk"></td>
                    <input type="hidden" name="hidden" value="overzichtBediendeBekijk">
                    <input type="hidden" name="pakketID" value="${pakket.getPnr()}">
                </form>
                </tr>
            </c:forEach>
        </table>
        
        <p>Voeg een pakket toe:</p>
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
                    <th>Voeg Toe</th>
                </tr>
                <tr>
                    <td><input type="text" name="naam"></td>
                    <td><input type="text" name="straat"></td>
                    <td><input type="text" name="nummer"></td>
                    <td><input type="text" name="postcode"></td>
                    <td><input type="text" name="gemeente"></td>
                    <td><input type="text" name="gewicht"></td>
                    <td><input type="text" name="commentaar"></td>
                    <td>
                        <select name="koerier" id="koerier">
                            <c:forEach var="koe" items="${sessionScope.koeriers}">
                                <option value='${koe.getKnr()}'>${koe.getKnaam().getGebruikersnaam()}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="submit" value="Voeg Toe"></td>
                    <input type="hidden" name="hidden" value="overzichtBediendeVoegtoe">
                </tr>
            </table>
        </form>
        <p>Log uit</p>
        <form method=post action="<c:url value="/GenericHandler"/>">
            <input type="submit" value="Log uit">
            <input type="hidden" name="hidden" value="logUit">
        </form>
    </body>
</html>
