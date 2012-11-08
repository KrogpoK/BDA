<%-- 
    Document   : Panier
    Created on : 4 nov. 2012, 18:58:34
    Author     : user
--%>

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
            if (request.getAttribute("listeOeuvre") != null) {
        %>
        <p>Resultats : ${requestScope.oeuvreList.size()}</p>
        <table border="3">
            <th>Titre</th><th>Genre</th><th>Date de parution</th><th>Support</th><th></th>
            <c:forEach var="film" begin="0" items="${requestScope.listeOeuvre}">
                <tr>
                    <td>${film.titre}</td> 
                    <td>${film.genre}</td> 
                    <td>${film.getStrDateParution()}</td> 
                    <td>${film.getStrType()}</td>
                    <td>
                        <form action="GestionPanier" method="post">
                            <input type="hidden" name="action" value="remove" />
                            <input type="hidden" name="oeuvreId" value="${film.getId()}" />
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
