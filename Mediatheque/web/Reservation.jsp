<%-- 
    Document   : Reservation
    Created on : 9 nov. 2012, 23:23:33
    Author     : user
--%>

<%@page import="enterprise.web_jpa_war.entity.mediatheque.Reservation"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Etat du client : </h1>

        <%
            if (request.getAttribute("listeEmprunts") != null) {
        %>
        <h1>liste Emprunts</h1>
        <p>Resultats : ${requestScope.listeEmprunts.size()}</p>
        <table border="3">
            <th>Titre</th><th>Genre</th><th>Date de parution</th><th>Support</th><th>Jours restants</th>           
            <c:forEach var="emprunt" begin="0" items="${requestScope.listeEmprunts}">
                <tr>
                    <td>${emprunt.oeuvre.titre}</td> 
                    <td>${emprunt.oeuvre.genre}</td> 
                    <td>${emprunt.oeuvre.getStrDateParution()}</td> 
                    <td>${emprunt.oeuvre.getStrType()}</td>

                </tr> 
            </c:forEach>
        </table>
        <%                }
        %>

        <%
            if (request.getAttribute("listeResaDispo") != null) {
        %>
        <h1>liste resa dispo</h1>
        <p>Resultats : ${requestScope.listeResaDispo.size()}</p>
        <table border="3">
            <th>Titre</th><th>Genre</th><th>Date de parution</th><th>Support</th><th>Jours restants</th>           
            <c:forEach var="resa" begin="0" items="${requestScope.listeResaDispo}">
                <tr>
                    <td>${resa.oeuvre.titre}</td> 
                    <td>${resa.oeuvre.genre}</td> 
                    <td>${resa.oeuvre.getStrDateParution()}</td> 
                    <td>${resa.oeuvre.getStrType()}</td>
                    <td>${resa.getJourDispoRestant()}</td>

                </tr> 
            </c:forEach>
        </table>
        <%                }
        %>


        <%
            if (request.getAttribute("listeResaAttente") != null) {
                int i=0;
        %>
        <h1>liste resa attente</h1>
        <p>Resultats : ${requestScope.listeResaAttente.size()}</p>
        <table border="3">
            <th>Titre</th><th>Genre</th><th>Date de parution</th><th>Support</th><th>place dans la file</th>           
            <c:forEach var="resa" begin="0" items="${requestScope.listeResaAttente}">
                <tr>
                    <td>${resa.oeuvre.titre}</td> 
                    <td>${resa.oeuvre.genre}</td> 
                    <td>${resa.oeuvre.getStrDateParution()}</td> 
                    <td>${resa.oeuvre.getStrType()}</td>
                    <td><% List<Integer> l = (List<Integer>)request.getAttribute("placeFileAttente"); 
                        out.print(l.get(i));
                        i++;
                    %></td>

                </tr> 
            </c:forEach>
        </table>
        <%                }
        %>
    </body>
</html>
