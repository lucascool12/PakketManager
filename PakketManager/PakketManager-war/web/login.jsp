<%-- 
    Document   : login
    Created on : 10-nov-2022, 9:07:19
    Author     : r0743857
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Log in:</h1>
        <form method="post" action="j_security_check">
            <input type="text" name="j_username">
            <input type="password" name="j_password">
            <input type="submit" value="Aanmelden">
        </form>
    </body>
</html>
