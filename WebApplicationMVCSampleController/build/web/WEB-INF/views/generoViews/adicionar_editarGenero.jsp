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
        <title>Editar Generos</title>
        <!-- Bootstraps -->
        <%@include file="/WEB-INF/jspf/head.jspf"%>
    </head>
    <body>
        
        <div class="container-fluid">
            <h1>Editar genero</h1>
            <form action="mvc?cmd=GeneroCmd&action=editado" method="post">
                <fieldset>
                    <div>
                        <label for="id"> ID</label> 
                        <input class="form-control" type="text" name="id" value="<c:out value="${genero.id}" />" readonly="readonly" placeholder="Genero ID" />
                    </div>
                    <div>
                        <label for="nome">Nome</label> 
                        <input class="form-control" type="text" name="nome" value="<c:out value="${genero.nome}" />" placeholder="nome" />
                    </div>              
                    <div>
                        <input class="btn btn-primary" type="submit" value="Submit" />
                    </div>
                </fieldset>
            </form>

        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>

        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>
    </body>
</html>
