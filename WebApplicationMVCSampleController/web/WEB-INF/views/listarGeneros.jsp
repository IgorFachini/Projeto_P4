<%-- 
    Document   : generos
    Created on : 30/10/2016, 16:14:32
    Author     : Cyber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generos</title>
        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">        
    </head>
    <body>
        <h1>Generos</h1>
        <table class="table" id="tableGenero" name="tableGenero" size="3" class="form-control">
            <caption>Afficher des Utlisateurs</caption>
            <tr>
                <th>Id</th>
                <th>Genero</th>    
            </tr>
            
            <c:forEach var="o" items="${generos}">
                <tr>

                    <td><c:out value="${o.id}" /></td>
                    <td><c:out value="${o.nome}" /></td>
                    

                </tr>
            </c:forEach>
        </table>


        <!-- CORE JAVASCRIPT LYBRARIES...
================================================== -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>
    </body>
</html>