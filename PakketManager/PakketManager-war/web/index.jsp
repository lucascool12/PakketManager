<%-- 
    Document   : text
    Created on : 14-nov-2022, 19:54:53
    Author     : Van Laer
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
        <h1>Welkom op onze mega coole pakketverdeling!</h1>
        <p>Zoek een pakket op:</p>
        <form method=post action="<c:url value="GenericHandler"/>">
            <table>
                <td><input type="text" name="pakketID"></td>
                <td><input type="submit" value="Zoek op"></td>
                <input type="hidden" name="hidden" value="indexGlobalLookup">
            </table>
        </form>
        <p>Log in als bediende:</p>
        <form method=post action="<c:url value="GenericHandler"/>">
            <input type="submit" value="Log in">
            <input type="hidden" name="hidden" value="indexGlobalLoginBediende">
        </form>
        <p>Log in als koerier:</p>
        <form method=post action="<c:url value="GenericHandler"/>">
            <input type="submit" value="Log in">
            <input type="hidden" name="hidden" value="indexGlobalLoginKoerier">
        </form>
        <p>Log uit</p>
        <form method=post action="<c:url value="/GenericHandler"/>">
            <input type="submit" value="Log uit">
            <input type="hidden" name="hidden" value="logUit">
        </form>
    </body>
</html>
