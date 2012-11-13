<%-- 
    Document   : rechercheOuvrageCritere
    Created on : 12 nov. 2012, 23:56:20
    Author     : jeremiefabre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h1>Recherche des Ouvrages</h1>
<div class="container-fluid well">
    <form id="searchFilmForm" action="RechercheOeuvre" method="post">
        <fieldset>
            <legend>Critères</legend>
            <label class="radio">
                <input type="radio" id="all" name="typeSupport" value="All" checked="true" />All           
            </label>
            <label class="radio">
                <input type="radio" id="film" name="typeSupport" value="Film" />Film
            </label>
            <label class="radio">
                <input type="radio" id="livre" name="typeSupport" value="Livre" />Livre
            </label>
            <label class="radio">
                <input type="radio" id="CD" name="typeSupport" value="CD" />CD
            </label>
            <label class="radio">
                <input type="radio" id="periodique" name="typeSupport" value="Periodique" />Periodique
            </label>
            Titre : 
            <input type="text" id="titreSearch" name="titreSearch" placeholder="titre" />

            Genre : 
            <select name="genreSearch">
                <option selected="true"></option>
                <option>Action</option>
                <option>Comique</option>
                <option>Fantasy</option>
                <option>Horreur</option>
                <option>Policier</option>
                <option>Romantique</option>
                <option>Thriller</option>
            </select> 
            Langue : 
            <select name="langueSearch">
                <option selected="true"></option>
                <option>Allemand</option>
                <option>Anglais</option>
                <option>Chinois</option>
                <option>Danois</option>
                <option>Espagnol</option>
                <option>Français</option>
                <option>Hongrois</option>
                <option>Italien</option>
                <option>Japonais</option>
                <option>Portuguais</option>
                <option>Russe</option>
                <option>Suédois</option>
                <option>Tchèque</option>
            </select>
            Date de Parution :
            <input type="date" id="dateParutionSearch" name="dateParutionSearch" placeholder="date de parution" />
            <label class="radio">
                <input type="radio" id="avant" name="dateParutionIndicateurSearch" value="avant" checked="true "/>avant
            </label>
            <label class="radio">
                <input type="radio" id="apres" name="dateParutionIndicateurSearch" value="apres" />après
            </label>
            Date d'Arrivée :
            <input type="date" id="dateArriveeSearch" name="dateArriveeSearch" placeholder="date d'arrivée" />
            <label class="radio">
                <input type="radio" id="avant" name="datArriveeIndicateurSearch" value="avant" checked="true "/></td>avant
            </label>
            <label class="radio">
                <input type="radio" id="apres" name="datArriveeIndicateurSearch" value="apres" /></td>après
            </label>
            Disponibilité : 
            <select name="disponibiliteSearch">
                <option selected="true"></option>
                <option>Oui</option>
                <option>Non</option>
            </select>
            Nombre d'Emprunts :
            <input type="text" id="nbEmpruntsSearch" name="nbEmpruntsSearch" placeholder="nombre d'emprunts" />
            <label class="radio">
                <input type="radio" id="avant" name="nbEmpruntsIndicateurSearch" value="avant" checked="true "/></td>moins
            </label>
            <label class="radio">
                <input type="radio" id="apres" name="nbEmpruntsIndicateurSearch" value="apres" /></td>plus
            </label>
            <div><input type="submit" class="btn btn-primary"id="find" value="Recherche" /></div>
        </fieldset>
    </form>
</div>