<%-- 
    Document   : DetailsOeuvre
    Created on : Nov 9, 2012, 11:45:25 PM
    Author     : isso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${film.getTitre()}</title>
    </head>
    <body>    
<table border="0" >
            <tr>
                <td>Titre</td>
                <td>${film.getTitre()}</td>
            </tr>
            <tr>
                <td>Realisateur:</td>
                <td>${film.getRealisateur()}</td>
            </tr>
            
            <tr>
                <td>Langue:</td>
                <td>${film.getLangue()}</td>
            </tr>
            
            <tr>
                <td>DUREE:</td>
                <td>${film.getDuree()}</td>
            </tr>
                       
           <tr>
                <td>Genre:</td>
                <td>${film.getGenre()}</td>
            </tr>
            <tr>
                <td>Date de parution:</td>
                <td>${film.getDateParution()}</td>
            </tr>

<c:forEach var="critique" begin="0" items="${requestScope.critiques}">
     
            <tr>
                  <td>
                      L'adherent:
                  </td>
                <td>
                    ${critique.getCompte().getProprietaire().getNom()}
                </td>
            </tr>
            <tr>
                <td>
                    Description: 
                </td>
                <td>${critique.description}</td>
            </tr> 
            <tr>
                <td>
                    Note:
                </td>
                <td>${critique.note}</td>
            </tr> 
            </c:forEach>
            <tr>
                <td colspan="2">
                    <form action="DetailsOeuvre" method="post">
                    <textarea cols="5" rows="9" name="description"></textarea>
                    <select name="note">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <input type="submit" value='go' />
                    <input type="hidden" name="id" value="${id}" />
                    <input type="hidden" name="oeuvreType" value="${oeuvreType}" />
                    </form>
                </td>
            </tr>
        </table>

    </body>
</html>
