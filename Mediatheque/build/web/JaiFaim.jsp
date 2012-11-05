<%-- 
    Document   : JaiFaim.jsp
    Created on : 17 oct. 2012, 11:32:00
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p><a href="index.jsp" ><-- revenir a l'accueil</a></p>
        <h1>J'ai faim!</h1>
        <img src ="http://www.mypizzabrothers.com/pizzapic.jpg" alt="pizza" />
        
        <p>${requestScope.date}</p>
    </body>
</html>
