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
        <title>Generos</title>
        <%@include file="/WEB-INF/jspf/head.jspf"%>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf"%>
        <div  class="container-fluid">

            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            <h1>Generos</h1>
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <p>
                            <a href="mvc?cmd=GeneroCmd&action=adicionar" data-toggle="modal" data-target="#util-modal"  class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                Adicionar</a>
                        </p>
                    </div><!-- /input-group -->
                </div><!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <form action="mvc?cmd=GeneroCmd&action=procurar" method="post">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Procurar pelo nome" id="cProcurar" name="cProcurar"/>
                            <span class="input-group-btn">
                                <input class="btn btn-warning" type="submit" value="Ir" />
                            </span>
                        </div>
                    </form>
                </div>
            </div>


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
                                <a href="mvc?cmd=GeneroCmd&action=detalhes&id=<c:out value="${o.id}"/>" class="btn btn-success" data-toggle="modal" data-target="#util-modal" data-placement="left" >
                                    <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                                </a> 
                                <a href="mvc?cmd=GeneroCmd&action=editar&id=<c:out value="${o.id}"/>" class="btn btn-warning" data-toggle="modal" data-target="#util-modal" data-placement="top" >
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                </a> 
                                <a href="mvc?cmd=GeneroCmd&action=deletar&id=<c:out value="${o.id}"/>" class="btn btn-danger"  data-placement="right" >
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </a> 

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>

        <%@include file="/WEB-INF/jspf/modal.jspf"%>
        <!-- CORE JAVASCRIPT LYBRARIES...
================================================== -->
        <%@include file="/WEB-INF/jspf/footer.jspf"%>

        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>
    </body>
</html>
