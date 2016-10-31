<%-- 
    Document   : editarGenero
    Created on : 30/10/2016, 16:20:24
    Author     : Cyber
--%>

<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="org.catolica.prog4.persistencia.entities.Genero"%>
<%@page import="org.catolica.prog4.persistencia.daos.GeneroDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Generos</title>
        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">        
    </head>
    <body>
        <h1>Editar genero</h1>
        <form action="GeneroCmd.do" method="post">
            <fieldset>
                <div>
                    <label for="id"> ID</label> <input type="text"
                                                              name="id" value="<c:out value="${genero.id}" />"
                                                              readonly="readonly" placeholder="Genero ID" />
                </div>
                <div>
                    <label for="nome">First Name</label> <input type="text"
                                                                     name="nome" value="<c:out value="${genero.nome}" />"
                                                                     placeholder="nome" />
                </div>              
                <div>
                    <input type="submit" value="Submit" />
                </div>
            </fieldset>
        </form>
    </body>
</html>
