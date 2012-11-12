<%-- 
    Document   : recherchePeriodiqueResultat
    Created on : 11 nov. 2012, 10:14:48
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h2>Resultats : ${requestScope.listFilms.size()}</h2>
<table border="3">
    <th>Titre</th>
    <th>Genre</th>
    <th>Date de parution</th>
    <th>Langue</th>
    <th>Thème</th>
    <th>Périodicité</th>
    <th></th>
    <c:forEach var="oeuvre" begin="0" items="${requestScope.listFilms}">
        <tr>
            <% String keyWord = (String) request.getAttribute("keyWord");
            %>
            <td>${oeuvre.getTitre(keyWord)}</td> 
            <td>${oeuvre.getGenre()}</td> 
            <td>${oeuvre.getStrDateParution()}</td> 
            <td>${oeuvre.getLangue()}</td> 
            <td>${oeuvre.getTheme()}</td>
            <td>${oeuvre.getPeriodicite()}</td>
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