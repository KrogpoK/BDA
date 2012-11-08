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
        <title>JSP Page</title>
    </head>
    <body>
        <p><a href="index.jsp" ><-- revenir a l'accueil</a></p>
        <p><a href="rechercheOeuvre.jsp" ><--rechercher une oeuvre</a></p>
        <h1>Contenu du panier</h1>

        <%
            if (request.getAttribute("listeOuvrage") != null) {
        %>
        <p>Resultats : ${requestScope.listeOuvrage.size()}</p>
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
                        <form action="GestionPanier" method="post">
                            <input type="hidden" name="action" value="remove" />
                            <input type="hidden" name="oeuvreId" value="${ouvrage.getId()}" />
                            <input type="submit" id="add" value="supprimer" />
                        </form>
                    </td>

                </tr> 
            </c:forEach>
        </table>
        <%                }
        %>
    </body>
</html>
