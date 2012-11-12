<%-- 
    Document   : rechercheOeuvre
    Created on : 1 nov. 2012, 11:31:47
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rechercher oeuvre</title>
    </head>
    <body>
        <p><a href="index.jsp" ><-- revenir a l'accueil</a></p>
        <p><a href="GestionPanier" ><-- Lister le panier</a></p>
        <%
            if (request.getAttribute("listFilms") != null) {
        %>
        <jsp:include page="rechercheFilmCritere.jsp" />
        <jsp:include page="rechercheFilmResultat.jsp" />
        <%            } else if (request.getAttribute("listLivres") != null) {
        %>
        <jsp:include page="rechercheLivreCritere.jsp" />
        <jsp:include page="rechercheLivreResultat.jsp" />
        <%            } else if (request.getAttribute("listCDs") != null) {
        %>
        <jsp:include page="rechercheCDCritere.jsp" />
        <jsp:include page="rechercheCDResultat.jsp" />
        <%            } else if (request.getAttribute("listPeriodiques") != null) {
        %>
        <jsp:include page="recherchePeriodiqueCritere.jsp" />
        <jsp:include page="recherchePeriodiqueResultat.jsp" />
        <%            } else {
        %>
        <jsp:include page="rechercheOeuvreCritere.jsp" />
        <jsp:include page="rechercheOeuvreResultat.jsp" />
        <%            }
        %>
    </body>
</html>
