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
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/design.css" rel="stylesheet">
        <title>Accueil</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" />

        <div class="container-fluid bloc">
            <h1>Accueil</h1>
            <%
                String prenom = ((Adherent) request.getSession().getAttribute("user")).getLogin();
            %>
            <div class="row-fluid">
                <div class="span12 well">
                    <div class="sidebar-nav">
                        <h3>Bonjour <% out.print(prenom);%></h3>
                        <ul>
                            <li><a href="rechercheOeuvre.jsp" >Espace Personnel</a></li>
                            <li><a href="rechercheOeuvre.jsp" >Rechercher des oeuvres</a></li>
                            <li><a href="rechercheOeuvre.jsp" >Critiquer des oeuvres</a></li>
                            <% if (((Adherent) request.getSession().getAttribute("user")).isAdmin()) {
                            %>
                            <li><a href="rechercheOeuvre.jsp" >Rechercher dans le stock</a></li>
                            <li><a href="rechercheOeuvre.jsp" >Gérer les emprunts</a></li>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp" />
    </body>
</html>
