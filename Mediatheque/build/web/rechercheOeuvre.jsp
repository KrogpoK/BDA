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
        <h1>Liste des Oeuvres</h1>
        <form id="searchFilmForm" action="RechercheOeuvre" method="post">
            <table>
                <tr>
                    <td><input type="radio" id="periodique" name="typeSupport" value="All" /></td><td>Periodique</td>
                    <td><input type="radio" id="film" name="typeSupport" value="Film" /></td><td>Film</td>
                    <td><input type="radio" id="livre" name="typeSupport" value="Livre" /></td><td>Livre</td>
                    <td><input type="radio" id="CD" name="typeSupport" value="CD" /></td><td>CD</td>
                    <td><input type="radio" id="periodique" name="typeSupport" value="Periodique" /></td><td>Periodique</td>
                </tr>
                <tr>
                    Titre : 
                <input type="text" id="titreSearch" name="titreSearch" placeholder="titre" />
                </tr>
                <tr>
                    Genre : 
                <select name="genreSearch">
                    <option selected="true"></option>
                    <option>Comique</option>
                    <option>Horreur</option>
                    <option>Policier</option>
                </select> 

                <input type="date" id="dateParutionSearch" name="dateParutionSearch" placeholder="date de parution" />
                <input type="radio" id="periodique" name="dateParutionAvantSearch" value="avant" /></td>avant
                <input type="radio" id="film" name="dateParutionAvantSearch" value="apres" /></td>après
                </tr>
                <tr>                    
                <input type="submit" id="find" value="find" />
                </tr>
            </table>
        </form>
        <br />
        <%
            if (request.getAttribute("listFilms") != null) {
        %>
        <p>Resultats : ${requestScope.listFilms.size()}</p>
        <table border="3">
            <th>Titre</th><th>Genre</th><th>Date de parution</th><th>Support</th><th></th>
            <c:forEach var="oeuvre" begin="0" items="${requestScope.listFilms}">
                <tr>
                    <% String keyWord = (String) request.getAttribute("keyWord");
                    %>
                    <td>${oeuvre.getTitre(keyWord)}</td> 
                    <td>${oeuvre.getGenre()}</td> 
                    <td>${oeuvre.getStrDateParution()}</td> 
                    <td>${oeuvre.getStrType()}</td>
                    <td>
                        <form action="GestionPanier" method="post">
                            <input type="hidden" name="action" value="add" />
                            <input type="hidden" name="oeuvreType" value="${oeuvre.getStrType()}" />
                            <input type="hidden" name="oeuvreId" value="${oeuvre.id}" />
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
