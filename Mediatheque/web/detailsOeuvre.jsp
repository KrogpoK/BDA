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
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/design.css" rel="stylesheet">
        <title>Critique d'une oeuvre</title>
    </head>
    <body>
        <%
            LinkedHashMap<String, String> keyVal = (LinkedHashMap<String, String>) request.getAttribute("keyVal");
            //out.print(keyVal.get("Titre"));
        %>
        <jsp:include page="Header.jsp" />

        <div class="container-fluid bloc">
            <h1>Critique d'une oeuvre</h1>
            <div class="row-fluid">
                <div class="span12 well">
                    <div class="sidebar-nav">
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
                                    <td><h4>Critique</h4></td>
                                    <td>
                                        Nom Adhérent : ${critique.getCompte().getProprietaire().getNom()}
                                    </td>
                                </tr>
                                <tr>
                                    <td>Description : ${critique.description}</td>
                                </tr> 
                                <tr>
                                    <td>Note : ${critique.note}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="2">
                                    <form action="DetailsOeuvre" method="post">
                                        <fieldset>
                                            Description : <textarea cols="50" rows="5" name="description"></textarea>
                                            Note :
                                            <select name="note">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5" checked="true">5</option>
                                            </select>
                                            <input type="submit" class="btn btn-primary" value='Critiquer' />
                                            <input type="hidden" name="id" value="${id}" />
                                            <input type="hidden" name="oeuvreType" value="${oeuvreType}" />
                                        </fieldset>
                                    </form>
                                </td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp" />
    </body>
</html>
