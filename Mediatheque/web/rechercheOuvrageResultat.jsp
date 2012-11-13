<%-- 
    Document   : rechercheOuvrageResultat
    Created on : 12 nov. 2012, 23:56:35
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h2>Resultats : ${requestScope.listOuvrages.size()}</h2>
<table border="3">
    <th>Titre</th>
    <th>Genre</th>
    <th>Date de parution</th>
    <th>Langue</th>
    <th>Support</th>
    <th>Date d'Arrivée</th>
    <th>Disponibilité</th>
    <th>Nombre d'Emprunts</th>
    <th></th>
    <c:forEach var="ouvrage" begin="0" items="${requestScope.listOuvrages}">
        <tr>
            <% String keyWord = (String) request.getAttribute("keyWord");
            %>
            <td>${ouvrage.getTitre(keyWord)}</td> 
            <td>${ouvrage.getGenre()}</td> 
            <td>${ouvrage.getStrDateParution()}</td> 
            <td>${ouvrage.getLangue()}</td> 
            <td>${ouvrage.getStrType()}</td>
            <td>${ouvrage.getDateArrivee()}</td>
            <td>${ouvrage.getDisponibilite()}</td>
            <td>${ouvrage.getNbEmprunts()}</td>
            <td>
                <form action="GestionPanier" method="post">
                    <input type="hidden" name="action" value="add" />
                    <input type="hidden" name="oeuvreType" value="${ouvrage.getStrType()}" />
                    <input type="hidden" name="oeuvreId" value="${ouvrage.id}" />
                    <input type="submit" id="add" value="ajouter au panier" />
                </form>
            </td>

        </tr> 
    </c:forEach>
</table>