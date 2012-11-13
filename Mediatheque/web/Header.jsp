<%-- 
    Document   : Header
    Created on : 13 nov. 2012, 20:06:07
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="navbar navbar-inverse navbar-fixed-top">
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
                    <a href="#" class="navbar-link">Copain</a>
                </p>
                <ul class="nav">
                    <li class="">
                        <a href="Accueil.jsp">Accueil</a>
                    </li>
                    <li class="">
                        <a href="sais">Espace Personnel</a>
                    </li>
                    <li class="">
                        <a href="./getting-started.html">Recherche Oeuvre</a>
                    </li>
                    <li class="">
                        <a href="./getting-started.html">Critique Oeuvre</a>
                    </li>
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