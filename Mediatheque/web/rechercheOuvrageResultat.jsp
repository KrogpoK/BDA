<%-- 
    Document   : rechercheOuvrageResultat
    Created on : 12 nov. 2012, 23:56:35
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h3>Resultats : ${requestScope.listOuvrages.size()}</h3>
<div class="container-fluid well">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>Titre</th>
                <th>Genre</th>
                <th>Date de parution</th>
                <th>Langue</th>
                <th>Support</th>
                <th>Date d'Arrivée</th>
                <th>Disponibilité</th>
                <th>Nombre d'Emprunts</th>
            </tr>
        </thead>
        <tbody>
            <% int cpt = 0;%>
            <c:forEach var="ouvrage" begin="0" items="${requestScope.listOuvrages}">
                <tr>
                    <% String keyWord = (String) request.getAttribute("keyWord");
                    %>
                    <td><% out.print(cpt);%></td> 
                    <td>${ouvrage.getTitre(keyWord)}</td> 
                    <td>${ouvrage.getGenre()}</td> 
                    <td>${ouvrage.getStrDateParution()}</td> 
                    <td>${ouvrage.getLangue()}</td> 
                    <td>${ouvrage.getStrType()}</td>
                    <td>${ouvrage.getDateArrivee()}</td>
                    <td>${ouvrage.getDisponibilite()}</td>
                    <td>${ouvrage.getNbEmprunts()}</td>
                </tr> 
                <% cpt++;%>
            </c:forEach>
        </tbody>
    </table>
</div>