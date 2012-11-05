<%@page import="enterprise.web_jpa_war.entity.Adherent"%>
<!--
  Copyright (c) 2010, Oracle. All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

  * Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

  * Neither the name of Oracle nor the names of its contributors
    may be used to endorse or promote products derived from this software without
    specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
  THE POSSIBILITY OF SUCH DAMAGE.
-->

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
    </head>
    <body>
        <%
            //si la session n'existe pas
            if (request.getSession().getAttribute("user") == null) {
        %>
        <h1>Connexion</h1>
        <form action="Connexion" method="post">
            <%
                if (request.getAttribute("error") != null) {
            %>
            <p style="color:red;" ><strong>Erreur danc le couple login/mot de passe</strong></p>      
            <%                }
            %>
            <table>
                <tr>
                    <td>login</td>
                    <td><input type="text" name ="login" /></td>
                </tr>
                <tr>
                    <td>password</td>
                    <td><input type="password" name ="pass" /></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="connexion" />
            <input type="submit" value="connexion" />
        </form>

        <%
            //Si il y a une session en cours
        } else {
            //si l'utilisateur est un administrateur
            if (((Adherent) request.getSession().getAttribute("user")).isAdmin()) {
        %>
        <jsp:forward page="AccueilAdmin.jsp"></jsp:forward>
        <%                    } //si l'utilisateur est un client
        else {
        %>
        <jsp:forward page="AccueilClient.jsp"></jsp:forward>
        <%                            }
            }
        %>

           <a href="RegenDb" >creer des choses dans la base</a>

    </body>
</html>
