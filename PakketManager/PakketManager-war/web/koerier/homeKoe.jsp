<%-- 
    Document   : homeKoe
    Created on : 29-dec-2022, 20:45:13
    Author     : Maurits
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
        <h1>Welkom koerier ${request.getUserPrincipal().getName()}!</h1>
        <p>Klik hier om uw pakketten te bekijken:</p>
        <form method=post action="<c:url value="/GenericHandler"/>">
                <input type="submit" value="Bekijk">
                <input type="hidden" name="hidden" value="bufferKoerierOverzicht">
            </form>
    </body>
</html>
