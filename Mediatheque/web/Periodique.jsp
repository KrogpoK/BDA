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
        <title>${periodique.getTitre()}</title>
    </head>
    <body>    
<table border="0" >
            <tr>
                <td>Titre:</td>
                <td>${periodique.getTitre()}</td>
            </tr>
            <tr>
                <td>theme:</td>
                <td>${periodique.getTheme()}</td>
            </tr>
            
            <tr>
                <td>type:</td>
                <td>${periodique.getType()}</td>
            </tr>

           <tr>
                <td>periodicite:</td>
                <td>${periodique.getPeriodicite()}</td>
            </tr>                  
           <tr>
                <td>Genre:</td>
                <td>${periodique.getGenre()}</td>
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
        </table>

    </body>
</html>
