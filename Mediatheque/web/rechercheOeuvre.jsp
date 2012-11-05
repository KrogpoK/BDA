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
        <h1>Liste des Oeuvres</h1>
        <form id="searchFilmForm" action="RechercheOeuvre" method="post">
            <table>
                <tr>
                    <td><input type="checkbox" id="isFilm" name="Film" /></td><td>Film&nbsp;&nbsp;</td>
                    <td><input type="checkbox" id="isLivre" name="Livre" /></td><td>Livre&nbsp;&nbsp;</td>
                    <td><input type="checkbox" id="isCD" name="CD" /></td><td>CD&nbsp;&nbsp;</td>
                    <td><input type="checkbox" id="isPeriodique" name="Periodique" /></td><td>Periodique&nbsp;&nbsp;</td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>mot clef</td>
                    <td><input type="text" id = "motsClef" name="motsClef" /></td>
                    <td><input type="submit" id="find" value="find" /></td>
                </tr>
            </table>
        </form>
        <br />
        <%
            if (request.getAttribute("oeuvreList") != null) {
        %>
        <p>Resultats : ${requestScope.oeuvreList.size()}</p>
        <table border="3">
            <th>Titre</th><th>Genre</th><th>Date de parution</th><th>Support</th><th></th>
            <c:forEach var="film" begin="0" items="${requestScope.oeuvreList}">
                <tr>
                    <% String kw = (String)request.getAttribute("kw");
                    %>
                    <td>${film.getTitre(kw)}</td> 
                    <td>${film.getGenre(kw)}</td> 
                    <td>${film.getStrDateParution()}</td> 
                    <td>${film.getStrType()}</td>
                    <td>
                        <form action="AjouterPanier" method="post">
                            <input type="hidden" name="oeuvreId" value="${film.getId()}" />
                            <input type="hidden" name="oeuvreType" value="${film.getStrType()}" />
                            <input type="submit" id="add" value="ajouter au panier" />
                        </form>
                    </td>

                </tr> 
            </c:forEach>
        </table>
        <%                }
        %>

    </body>
</html>
