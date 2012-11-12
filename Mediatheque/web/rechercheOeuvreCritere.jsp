<%-- 
    Document   : rechercheOeuvreCritere
    Created on : 11 nov. 2012, 10:16:37
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h1>Recherche des Oeuvres</h1>
<form id="searchFilmForm" action="RechercheOeuvre" method="post">
    <table>
        <tr>
            <td><input type="radio" id="all" name="typeSupport" value="All" checked="true" /></td><td>All</td>
            <td><input type="radio" id="film" name="typeSupport" value="Film" /></td><td>Film</td>
            <td><input type="radio" id="livre" name="typeSupport" value="Livre" /></td><td>Livre</td>
            <td><input type="radio" id="CD" name="typeSupport" value="CD" /></td><td>CD</td>
            <td><input type="radio" id="periodique" name="typeSupport" value="Periodique" /></td><td>Periodique</td>
        </tr>
        <tr>
            Titre : 
        <input type="text" id="titreSearch" name="titreSearch" placeholder="titre" />
        </tr>
        <tr>
            Genre : 
        <select name="genreSearch">
            <option selected="true"></option>
            <option>Comique</option>
            <option>Horreur</option>
            <option>Policier</option>
        </select> 
        Langue : 
        <select name="langueSearch">
            <option selected="true"></option>
            <option>Français</option>
            <option>Anglais</option>
        <input type="date" id="dateParutionSearch" name="dateParutionSearch" placeholder="date de parution" />
        <input type="radio" id="avant" name="dateParutionIndicateurSearch" value="avant" checked="true "/></td>avant
        <input type="radio" id="apres" name="dateParutionIndicateurSearch" value="apres" /></td>après
        </tr>
</table>
<input type="submit" id="find" value="Find" />
</form>
<hr />