<%@page import="enterprise.web_jpa_war.entity.Adherent"%>
<%-- 
    Document   : Header
    Created on : 13 nov. 2012, 20:06:07
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Médiathèque</a>
            <div class="nav-collapse collapse">
                <p class="navbar-text pull-right">
                    <a href="#" class="navbar-link"><% out.print(((Adherent) request.getSession().getAttribute("user")).getLogin());%></a>
                </p>
                <ul class="nav">
                    <li class="">
                        <a href="Accueil.jsp">Accueil</a>
                    </li>
                    <li class="">
                        <a href="GestionReservation">Espace Personnel</a>
                    </li>
                    <li class="">
                        <a href="rechercheOeuvre.jsp">Recherche Oeuvre</a>
                    </li>
                    <% if (((Adherent) request.getSession().getAttribute("user")).isAdmin()) {
                    %>
                    <li><a href="rechercheOuvrage.jsp" >Recherche Stock</a></li>
                    <li><a href="GestionEmprunt.jsp" >Gérer Emprunts</a></li>
                    <% }%>
                    <li>
                        <form action="Connexion" method="post" >
                            <input type="hidden" name="action" value="deconnexion" />
                            <input type="submit" class="btn" value="Déconnecter" />
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>