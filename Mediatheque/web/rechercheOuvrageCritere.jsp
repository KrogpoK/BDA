<%-- 
    Document   : rechercheOuvrageCritere
    Created on : 12 nov. 2012, 23:56:20
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h1>Recherche des Ouvrages</h1>
<form id="searchFilmForm" action="RechercheOuvrage" method="post">
    <table>
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
        </select>
        <input type="date" id="dateParutionSearch" name="dateParutionSearch" placeholder="date de parution" />
        <input type="radio" id="avant" name="dateParutionIndicateurSearch" value="avant" checked="true "/></td>avant
        <input type="radio" id="apres" name="dateParutionIndicateurSearch" value="apres" /></td>après
        <input type="date" id="dateArriveeSearch" name="dateArriveeSearch" placeholder="date d'arrivée" />
        <input type="radio" id="avant" name="datArriveeIndicateurSearch" value="avant" checked="true "/></td>avant
        <input type="radio" id="apres" name="datArriveeIndicateurSearch" value="apres" /></td>après
        Disponibilité : 
        <select name="disponibiliteSearch">
            <option selected="true"></option>
            <option>Oui</option>
            <option>Non</option>
        </select>
        <input type="text" id="nbEmpruntsSearch" name="nbEmpruntsSearch" placeholder="nombre d'emprunts" />
        <input type="radio" id="avant" name="nbEmpruntsIndicateurSearch" value="avant" checked="true "/></td>avant
        <input type="radio" id="apres" name="nbEmpruntsIndicateurSearch" value="apres" /></td>après
        </tr>
    </table>
    <input type="submit" id="find" value="Find" />
</form>
<hr />