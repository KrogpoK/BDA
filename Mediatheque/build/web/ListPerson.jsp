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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/design.css" rel="stylesheet">
        <title>Liste Personnes</title>
    </head>
    <body>
        <div class="container-fluid bloc">
            <div><a href="index.jsp" >Revenir à la page précédente</a></div><br />
            <h1>Liste de personnes enregistrés dans la base de données (${requestScope.adherentList.size()})</h1>
            <div class="row-fluid">
                <div class="span12 well">
                    <div class="sidebar-nav">
                        <table class="table table-hover" id="personListTable">
                            <thead>
                                <tr >
                                    <th bgcolor=>ID</th>
                                    <th bgcolor=>prenom</th>
                                    <th bgcolor=>nom</th>
                                    <th bgcolor=>mail</th>
                                    <th bgcolor=>login</th>
                                    <th bgcolor=>pass</th>
                                    <th bgcolor=>admin</th>
                                    <th bgcolor=>date de naissance</th>
                                    <th bgcolor=>rue</th>
                                    <th bgcolor=>CP</th>
                                    <th bgcolor=>Ville</th>
                                    <th bgcolor=>pays</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="person" begin="0" items="${requestScope.adherentList}">
                                    <tr>
                                        <td>${person.id}&nbsp;&nbsp;</td> 
                                        <td>${person.prenom}&nbsp;&nbsp;</td> 
                                        <td>${person.nom}&nbsp;&nbsp;</td> 
                                        <td>${person.mail}&nbsp;&nbsp;</td> 
                                        <td>${person.login}&nbsp;&nbsp;</td> 
                                        <td>${person.pass}&nbsp;&nbsp;</td> 
                                        <td>${person.admin}&nbsp;&nbsp;</td> 
                                        <td>${person.getStrDateDeNaissance()}&nbsp;&nbsp;</td> 
                                        <td>${person.adress.street}&nbsp;&nbsp;</td> 
                                        <td>${person.adress.city.postalCode}&nbsp;&nbsp;</td> 
                                        <td>${person.adress.city.name}&nbsp;&nbsp;</td> 
                                        <td>${person.adress.country}&nbsp;&nbsp;</td> 

                                    </tr> 

                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
