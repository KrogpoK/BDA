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
        <title>${livre.getTitre()}</title>
    </head>
    <body>    
<table border="0" >
            <tr>
                <td>Titre</td>
                <td>${livre.getTitre()}</td>
            </tr>
            <tr>
                <td>auteur</td>
                <td>${livre.getAuteur()}</td>
            </tr>
            
            <tr>
                <td>editeur</td>
                <td>${livre.getEditeur()}</td>
            </tr>

                       
           <tr>
                <td>Genre:</td>
                <td>${livre.getGenre()}</td>
            </tr>
<c:forEach var="critique" begin="0" items="${requestScope.critiques}">
                 <tr>
                <td>
                    ${critique.getCompte().getProprietaire().getNom()}
                </td>
            </tr>
            <tr>
                <td>${critique.description}</td>
            </tr> 
            <tr>
                <td>${critique.note}</td>
            </tr> 
            </c:forEach>
        </table>
    </body>
</html>

