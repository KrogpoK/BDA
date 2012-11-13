<%-- 
    Document   : AccueilAdmin
    Created on : 5 nov. 2012, 02:35:32
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>Espace administrateur</h1>
        <p> que voulez vous faire ?</p>
        <ul>
            <li><a href ="JaiFaim.jsp" >manger Une pizza</a></li>
            <li><a href="ListPersonne" >Lister les adherents</a></li>
            <li><a href="rechercheOeuvre.jsp" >rechercher une oeuvre</a></li>
            <li><a href="CreatePerson.jsp">Creer une personne</a></li>
            <li><a href="RegenDb" >creer des choses dans la base</a></li>
            <li><a href="GestionEmprunt.jsp" >Gerer les emprunts </a></li>
        </ul>
        <form action="Connexion" method="post" >
            <input type="hidden" name="action" value="deconnexion" />
            <input type="submit" value="deconnecter" />
        </form>
    </body>
</html>
