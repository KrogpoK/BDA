<%-- 
    Document   : EmpruntRetour
    Created on : 10 nov. 2012, 15:52:16
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
        <title>Gestion Emprunts</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" />

        <div class="container-fluid bloc">
            <h1>Rendre un ouvrage</h1>
            <div class="row-fluid">
                <div class="span12 well">
                    <div class="sidebar-nav">
                        <form action ="GestionEmprunt" method="post">
                            <fieldset>
                                <input type ="hidden" name="action" value="list" />
                                <table>
                                    <tr>
                                        <td>nom</td>
                                        <td><input type="text" name="nom" /></td>
                                    </tr>
                                    <tr>
                                        <td>prenom</td>
                                        <td><input type="text" name="prenom" /></td>
                                    </tr>
                                    <tr>
                                        <td>date de naissance</td>
                                        <td><input type="date" name="date" /></td>
                                    </tr>
                                </table>
                                <input type="submit" class="btn btn-primary" value="Rechercher" />
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="sidebar-nav">
                        <%
                            System.out.println("dans jsp, error : " + request.getAttribute("error"));
                            if (request.getAttribute("error") == null) {
                                if (request.getAttribute("listeEmprunts") == null) {
                        %>

                        <h2>Il n'y a pas d'emprunts actifs</h2>
                        <% } else {
                        %>
                        <h3>Liste des emprunts </h3>
                        <table>
                            <th>Titre</th><th>genre</th><th>date emprunt</th><th>type</th><th></th>          
                            <c:forEach var="emprunt" begin="0" items="${requestScope.listeEmprunts}">
                                <tr>
                                    <td>${emprunt.eOuvrage.oeuvre.titre}</td> 
                                    <td>${emprunt.eOuvrage.oeuvre.genre}</td> 
                                    <td>${emprunt.getStrDateDebut()}</td>
                                    <td>
                                        <a href="GestionEmprunt?action=rendre&idEmprunt=${emprunt.getId()}" ><input type ="button" value="retourner" /></a> 
                                    </td>
                                </tr> 
                            </c:forEach>
                        </table>

                        <%            }
                            if (request.getAttribute("listeReservation") == null) {
                        %>

                        <h4>Il n'y a pas de reservations actives</h4>
                        <%  } else {
                        %>
                        <h3>Liste des reservations </h3>
                        <table>
                            <th>Titre</th><th>genre</th><th>type</th><th></th>          
                            <c:forEach var="resa" begin="0" items="${requestScope.listeReservation}">
                                <tr>
                                    <td>${resa.oeuvre.titre}</td> 
                                    <td>${resa.oeuvre.genre}</td>
                                    <td>
                                        <a href="GestionEmprunt?action=creer&idOeuvre=${resa.oeuvre.id}&idAdherent=${resa.compte.proprietaire.id}&idResa=${resa.id}" ><input type ="button" value="emprunter" /></a> 
                                    </td>
                                </tr> 
                            </c:forEach>
                        </table>
                        <%  }
                            } //si il y a eu une erreur
                            else {
                                out.println("<h2>erreur : " + request.getAttribute("error") + "</h2>");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />
    </body>
</html>
