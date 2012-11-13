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
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/design.css" rel="stylesheet">
        <title>Rechercher Oeuvre</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" />
        <div class="container-fluid bloc">
            <div class="row-fluid">
                <div class="span12">
                    <div class="sidebar-nav">
                        <div><a href="GestionPanier" >Lister le panier</a></div>
                        <%
                            if (request.getAttribute("listFilms") != null) {
                        %>
                        <jsp:include page="rechercheFilmCritere.jsp" />
                        <%  } else if (request.getAttribute("listLivres") != null) {
                        %>
                        <jsp:include page="rechercheLivreCritere.jsp" />
                        <%  } else if (request.getAttribute("listCDs") != null) {
                        %>
                        <jsp:include page="rechercheCDCritere.jsp" />
                        <%  } else if (request.getAttribute("listPeriodiques") != null) {
                        %>
                        <jsp:include page="recherchePeriodiqueCritere.jsp" />
                        <%  } else {
                        %>
                        <jsp:include page="rechercheOeuvreCritere.jsp" />
                        <%  }
                        %>
                        <hr />
                        <%
                            if (request.getAttribute("listFilms") != null) {
                        %>
                        <jsp:include page="rechercheFilmResultat.jsp" />
                        <%  } else if (request.getAttribute("listLivres") != null) {
                        %>
                        <jsp:include page="rechercheLivreResultat.jsp" />
                        <%  } else if (request.getAttribute("listCDs") != null) {
                        %>
                        <jsp:include page="rechercheCDResultat.jsp" />
                        <%  } else if (request.getAttribute("listPeriodiques") != null) {
                        %>
                        <jsp:include page="recherchePeriodiqueResultat.jsp" />
                        <%  } else {
                        %>
                        <jsp:include page="rechercheOeuvreResultat.jsp" />
                        <%  }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />

        <script src="css/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
