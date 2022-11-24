<%-- 
    Document   : test_databean
    Created on : 24-nov-2022, 8:57:43
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test pagina</title>
    </head>
    <body>
        <form action="POST" method="Test">
            <button type="submit" name="getPakketen"/><br/>
            <input type="text" name="getPakketen_knr"/><br/>
            <button type="submit" name="getPakketen_knr_submit"/><br/>
            <input type="text" name="getPakket"/><br/>
            <button type="submit" name="getPakket_submit"/><br/>
            <input type="text" name="getKoerier"/><br/>
            <button type="submit" name="getKoerier_submit"/><br/>
            <input type="text" name="getPakketStatus"/><br/>
            <button type="submit" name="getPakketStatus_submit"/><br/>
        </form>
    </body>
</html>
