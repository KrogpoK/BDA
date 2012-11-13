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
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/design.css" rel="stylesheet">
        <title>Rechercher Ouvrage</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" />

        <div class="container-fluid bloc">
            <div class="row-fluid">
                <div class="span12">
                    <div class="sidebar-nav">
                        <jsp:include page="rechercheOuvrageCritere.jsp" />
                        <jsp:include page="rechercheOuvrageResultat.jsp" />
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp" />
    </body>
</html>
