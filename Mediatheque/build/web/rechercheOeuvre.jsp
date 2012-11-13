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
        <link href="css/design.css" rel="stylesheet">
        <title>Rechercher Oeuvre</title>
    </head>
    <body>
        <!-- Navbar
 ================================================== -->
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="./index.html">Médiathèque</a>
                    <div class="nav-collapse collapse">
                        <p class="navbar-text pull-right">
                            <a href="#" class="navbar-link">Copain</a>
                        </p>
                        <ul class="nav">
                            <li class="">
                                <a href="./index.html">Accueil</a>
                            </li>
                            <li class="active">
                                <a href="./getting-started.html">Recherche Oeuvre</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid bloc">
            <div class="row-fluid">
                <div class="span12">
                    <div class="sidebar-nav">
                        <p><a href="index.jsp" ><-- revenir a l'accueil</a></p>
                        <p><a href="GestionPanier" ><-- Lister le panier</a></p>
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

        <!-- Footer
================================================== -->
        <footer class="footer">
            <div class="container">
                <p class="pull-right"><a href="#">Retour en haut</a></p>
                <p>Site web crée par l'équipe FARREZ</p>
                <p>© Tout droits réservés</p>
            </div>
        </footer>
        
        <script src="css/bootstrap/js/bootstrap.js"></script>

    </body>
</html>
