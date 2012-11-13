<%-- 
    Document   : rechercheCDResultat
    Created on : 11 nov. 2012, 10:14:34
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h3>Resultats : ${requestScope.listCDs.size()}</h3>
<div class="container-fluid well">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>Titre</th>
                <th>Genre</th>
                <th>Date de parution</th>
                <th>Langue</th>
                <th>Artiste</th>
                <th>Maison d'Edition</th>
                <th>Panier</th>
                <th>Critique</th>
            </tr>
        </thead>
        <tbody>
            <% int cpt = 0;%>
            <c:forEach var="oeuvre" begin="0" items="${requestScope.listCDs}">
                <tr>
                    <% String keyWord = (String) request.getAttribute("keyWord");
                    %>
                    <td><% out.print(cpt);%></td> 
                    <td>${oeuvre.getTitre(keyWord)}</td> 
                    <td>${oeuvre.getGenre()}</td> 
                    <td>${oeuvre.getStrDateParution()}</td> 
                    <td>${oeuvre.getLangue()}</td> 
                    <td>${oeuvre.getInterprete()}</td>
                    <td>${oeuvre.getMaisonEdition()}</td>
                    <td>
                        <form action="GestionPanier" method="post">
                            <input type="hidden" name="action" value="add" />
                            <input type="hidden" name="oeuvreType" value="${oeuvre.getStrType()}" />
                            <input type="hidden" name="oeuvreId" value="${oeuvre.id}" />
                            <input type="submit" class="btn" id="add" value="X" />
                        </form>
                    </td>
                    <td>
                        <a class="btn" href="DetailsOeuvre?id=${oeuvre.getId()}&oeuvreType=${oeuvre.getStrType()}">X</a> 
                    </td>
                </tr> 
                <% cpt++;%>
            </c:forEach>
        </tbody>
    </table>
</div>