<%-- 
    Document   : AccueilClient
    Created on : 5 nov. 2012, 02:35:46
    Author     : user
--%>

<%@page import="enterprise.web_jpa_war.entity.Adherent"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>Espace client</h1>
       <%
       String prenom = ((Adherent)request.getSession().getAttribute("user")).getLogin();
       %>
       <h2>Bonjour <% out.print(prenom); %></h2>
        <p> que voulez vous faire ?</p>
        <ul>
            <li><a href ="JaiFaim.jsp" >manger Une pizza</a></li>
            <li><a href="rechercheOeuvre.jsp" >rechercher une oeuvre</a></li>
        </ul>
        <form action="Connexion" method="post" >
            <input type="hidden" name="action" value="deconnexion" />
            <input type="submit" value="deconnecter" />
        </form>
    </body>
</html>
