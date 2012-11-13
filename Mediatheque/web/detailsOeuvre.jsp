<%-- 
    Document   : detailsOeuvre
    Created on : Nov 13, 2012, 4:49:32 PM
    Author     : isso
--%>

<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.HashMap"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><% 
                LinkedHashMap<String,String> keyVal = (LinkedHashMap<String,String>)request.getAttribute("keyVal");
                out.print(keyVal.get("Titre")); %></title>
    </head>
    <body>
        <table>
        <%
         for (String s : keyVal.keySet()) {
             out.print("<tr>");
                out.print("<td>");
                    out.print(s);
                out.print("</td>");
                out.print("<td>");
                    out.print(keyVal.get(s));
                out.print("</td>");
             out.print("</tr>");
         }
        %>
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
