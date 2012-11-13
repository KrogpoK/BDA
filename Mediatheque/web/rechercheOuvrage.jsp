<%-- 
    Document   : rechercheOuvrage
    Created on : 12 nov. 2012, 23:54:36
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rechercher Ouvrage</title>
    </head>
    <body>
        <jsp:include page="rechercheOuvrageCritere.jsp" />
        <jsp:include page="rechercheOuvrageResultat.jsp" />
    </body>
</html>
