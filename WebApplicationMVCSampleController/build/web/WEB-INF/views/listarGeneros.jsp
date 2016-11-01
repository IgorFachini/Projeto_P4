<%-- 
    Document   : generos
    Created on : 30/10/2016, 16:14:32
    Author     : Cyber
--%>

<%@page import="org.catolica.prog4.persistencia.entities.Genero"%>
<%@page import="org.catolica.prog4.persistencia.daos.GeneroDAO"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
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
        <link href="../../assets/jquery/jquery.bootgrid.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">        
    </head>
    <body class="container-fluid">  
        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
        <h1>Generos</h1>
        <div class="row">
            <div class="col-lg-6">
                <div class="input-group">
                    <p>
                        <a href="mvc?cmd=GeneroCmd&action=adicionar" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            Adicionar</a>
                    </p>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
            <div class="col-lg-6">
                <div class="input-group" href="mvc?cmd=GeneroCmd&action=procurar">
                    <input type="text" class="form-control" placeholder="Procurar pelo id" id="cProcurar" name="cProcurar"/>
                    <span class="input-group-btn">
                        <a   class="btn btn-default">Ir</a>
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div><!-- /.row -->


        <table id="grid-basic" class="table table-condensed table-hover table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Genero</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${generos}">
                    <tr>
                        <td><c:out value="${o.id}" /></td>
                        <td><c:out value="${o.nome}" /></td>
                        <td>
                            <a href="mvc?cmd=GeneroCmd&action=detalhes&id=<c:out value="${o.id}"/>" class="btn btn-success" data-toggle="tooltip" data-placement="left" >
                                <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                            </a> 
                            <a href="mvc?cmd=GeneroCmd&action=editar&id=<c:out value="${o.id}"/>" class="btn btn-warning" data-toggle="tooltip" data-placement="top" >
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                            </a> 
                            <a href="mvc?cmd=GeneroCmd&action=deletar&id=<c:out value="${o.id}"/>" class="btn btn-danger" data-toggle="tooltip" data-placement="right" >
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a> 

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <!-- CORE JAVASCRIPT LYBRARIES...
================================================== -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script src="../../assets/jquery/jquery.bootgrid.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>
    </body>
</html>
