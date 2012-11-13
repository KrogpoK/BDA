<%-- 
    Document   : Panier
    Created on : 4 nov. 2012, 18:58:34
    Author     : user
--%>

<%@page import="enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/design.css" rel="stylesheet">
        <title>Gestion du Panier</title>    
    </head>
    <body>
        <jsp:include page="Header.jsp" />

        <div><a href="rechercheOeuvre.jsp" >Rechercher une oeuvre</a></div>
        <div class="container-fluid bloc">
            <h1>Contenu du panier</h1>
            <div class="row-fluid">
                <div class="span12 well">
                    <div class="sidebar-nav">
                        <%
                            if (request.getAttribute("listeOuvrage") != null) {
                        %>
                        <p>Resultats : ${requestScope.listeOuvrage.size()}</p>
                        <form action="GestionReservation" method="post">
                            <fieldset>
                                <table border="3">
                                    <th>Titre</th><th>Genre</th><th>Date de parution</th><th>Support</th><th>est disponible</th><th></th>           
                                    <c:forEach var="ouvrage" begin="0" items="${requestScope.listeOuvrage}">
                                        <tr>
                                            <td>${ouvrage.oeuvre.titre}</td> 
                                            <td>${ouvrage.oeuvre.genre}</td> 
                                            <td>${ouvrage.oeuvre.getStrDateParution()}</td> 
                                            <td>${ouvrage.oeuvre.getStrType()}</td>
                                            <c:choose>    
                                                <c:when test="${ouvrage.disponibilite == 0 }"><td>oui</td></c:when>
                                                <c:when test="${ouvrage.disponibilite != 0 }"><td>non</td></c:when>
                                            </c:choose>
                                            <td>
                                                <a href="GestionPanier?action=remove&oeuvreId=${ouvrage.oeuvre.getId()}" ><input type ="button" value="X" /></a> 
                                            </td>
                                        <input type="hidden" name="idOeuvreList[]" value="${ouvrage.oeuvre.getId()}"/>
                                        <input type="hidden" name="typeOeuvre[]" value="${ouvrage.oeuvre.getStrType()}"/>
                                        </tr> 
                                    </c:forEach>
                                </table>
                                <input type="hidden" name="kikou" value="pouet"/> 
                                <input type="submit" class="btn btn-primary" value="Réserver" />
                            </fieldset>
                        </form>
                        <%                }
                        %>
                    </div>
                </div>
            </div>

            <jsp:include page="Footer.jsp" />
    </body>
</html>
