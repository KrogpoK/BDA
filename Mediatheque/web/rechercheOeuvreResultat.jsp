<%-- 
    Document   : rechercheOeuvreResultat
    Created on : 11 nov. 2012, 10:16:52
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h2>Resultats : ${requestScope.listOeuvres.size()}</h2>
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
                <th>Panier</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="oeuvre" begin="0" items="${requestScope.listOeuvres}">
                <tr>
                    <% String keyWord = (String) request.getAttribute("keyWord");
                    %>
                    <td>id</td> 
                    <td>${oeuvre.getTitre(keyWord)}</td> 
                    <td>${oeuvre.getGenre()}</td> 
                    <td>${oeuvre.getStrDateParution()}</td> 
                    <td>${oeuvre.getLangue()}</td> 
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
        </tbody>
    </table>
</div>