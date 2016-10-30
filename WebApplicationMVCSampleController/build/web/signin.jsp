<%-- 
    Document   : signin
    Created on : 26/10/2016, 21:22:35
    Author     : Cyber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="PM APP">
        <meta name="author" content="Geovane Camargo">
        <link rel="icon" href="assets/icons/qb-icon.png">
        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">

    </head>
    <body> 
        <div id="main" class="container-fluid">
            <BR>
            <h4 class="page-header">Login</h4>
            <!-- FORM MAIN -->
            <form id="formAddNew" name="formAddNew" method="POST" action="signin">
                <input type="hidden" id="cmd" name="cmd" value="adicionar">

                <div class="row">
                    <div class="form-group col-md-offset-4 col-md-4 ">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" required="required" placeholder="Digite o nome">

                        <label for="senha">Senha:</label>
                        <input type="password" class="form-control" id="senha" name="senha" required="required" placeholder="Digite sua senha">
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-offset-6 col-md-4">
                        <button type="submit" class="btn btn-primary">Entrar</button>
                        <div class="form-group col-md-offset-1">
                            ${msg}
                        </div>
                    </div>
                </div>
            </form>  
        </div>
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>        


    </body>
</html>
