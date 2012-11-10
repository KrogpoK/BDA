<%-- 
    Document   : EmpruntRetour
    Created on : 10 nov. 2012, 15:52:16
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p><a href="index.jsp" ><-- revenir a l'accueil</a></p>
        <h1>Rendre un ouvrage</h1>
        <form action ="GestionEmprunt" method="post">
            <input type ="hidden" name="action" value="list" />
            <table>
                <tr>
                    <td>nom</td>
                    <td><input type="text" name="nom" /></td>
                </tr>
                <tr>
                    <td>prenom</td>
                    <td><input type="text" name="prenom" /></td>
                </tr>
                <tr>
                    <td>date de naissance</td>
                    <td><input type="date" name="date" /></td>
                </tr>
            </table>
            <input type="submit" value="chercher" />
        </form>

        <%
            if (request.getAttribute("error") == null && request.getAttribute("listeEmprunt") != null) {
        %>
        <h2>Liste des emprunts </h2>
        <table>
            <th>Titre</th><th>genre</th><th>date emprunt</th><th>type</th><th></th>          
            <c:forEach var="emprunt" begin="0" items="${requestScope.listeEmprunt}">
                <tr>
                    <td>${emprunt.eOuvrage.oeuvre.titre}</td> 
                    <td>${emprunt.eOuvrage.oeuvre.genre}</td> 
                    <td>${emprunt.getStrDateDebut()}</td>
                    <td>
                        <a href="GestionEmprunt?action=rendre&idEmprunt=${emprunt.getId()}" ><input type ="button" value="retourner" /></a> 
                    </td>
                </tr> 
            </c:forEach>
        </table>

        <%  } else {%>
        <h1>${requestScope.error}</h1>
        <%  }

        %>
    </body>
</html>
