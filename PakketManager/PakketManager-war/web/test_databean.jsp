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
        <form action="GenericHandler" method="GET">
            <input type="hidden" name="Test" value="statusGlobalTerug">
            <button type="submit" name="getPakketen">getPakketen</button><br/>
            <br/>
            <input type="text" name="getPakketen_knr"/><br/>
            <button type="submit" name="getPakketen_knr_submit">getPakketen_knr</button><br/>
            <br/>
            <input type="text" name="getPakket"/><br/>
            <button type="submit" name="getPakket_submit">getPakket</button><br/>
            <br/>
            <input type="text" name="getKoerier"/><br/>
            <button type="submit" name="getKoerier_submit">getKoerier</button><br/>
            <br/>
            <input type="text" name="getPakketStatus"/><br/>
            <button type="submit" name="getPakketStatus_submit">getPakketStatus</button><br/>
            <br/>
            <label for="addPakket_pgewicht">pgewicht</label>
            <input type="text" name="addPakket_pgewicht"/><br/>
            <label for="addPakket_pstatus">pstatus</label>
            <input type="text" name="addPakket_pstatus"/><br/>
            <label for="addPakket_lnaam">lnaam</label>
            <input type="text" name="addPakket_lnaam"/><br/>
            <label for="addPakket_lstraat">lstraat</label>
            <input type="text" name="addPakket_lstraat"/><br/>
            <label for="addPakket_lnummer">lnummer</label>
            <input type="text" name="addPakket_lnummer"/><br/>
            <label for="addPakket_lpostcode">lpostcode</label>
            <input type="text" name="addPakket_lpostcode"/><br/>
            <label for="addPakket_lgemeente">lgemeente</label>
            <input type="text" name="addPakket_lgemeente"/><br/>
            <label for="addPakket_knr">knr</label>
            <input type="text" name="addPakket_knr"/><br/>
            <button type="submit" name="addPakket_submit">addPakket</button><br/>
            <br/>
            <button type="submit" name="getMaxPnr" value="bleep">getMaxPnr</button><br/>
            <br/>
            <label for="getStatussenAantal_status">status</label>
            <input type="text" name="getStatussenAantal_status"/><br/>
            <button type="submit" name="getStatussenAantal" value="bleep">getStatussenAantal</button><br/>
            <br/>
            <label for="setStatus_pnr">pnr</label>
            <input type="text" name="setStatus_pnr"/><br/>
            <label for="setStatus_status">status</label>
            <input type="text" name="setStatus_status"/><br/>
            <button type="submit" name="setStatus" value="bleep">setStatus</button><br/>
            <br/>
            <label for="getKoerier_username">username</label>
            <input type="text" name="getKoerier_username"/><br/>
            <button type="submit" name="_getKoerier" value="bleep">getKoerier</button><br/>
            <br/>
            <button type="submit" name="getKoeriers" value="bleep">getKoeriers</button><br/>
        </form>
    </body>
</html>