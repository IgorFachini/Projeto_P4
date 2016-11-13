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
        <meta name="author" content="Igor Fachini">
        <link rel="icon" href="assets/icons/qb-icon.png">
        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">
        <link rel="stylesheet" media="screen" href="assets/particles/css/style.css">

    </head>
    <body> 
        <div class="count-particles">
            <span class="js-count-particles">--</span> particles
        </div>
        <div id="particles-js">             
            <div class="row">
                <div class="form-group col-md-offset-4 col-md-4 ">
                    <div class="login">
                        <h1 class="login-heading">
                            <strong>Bem vindo.</strong> Logue-se por favor</h1>
                        <form method="post" action="signin">
                            <input type="text" name="email" placeholder="Email" required="required" class="input-txt" />
                            <input type="password" name="senha" placeholder="Senha" required="required" class="input-txt" />
                            <div class="login-footer">
                                <a href="#" class="lnk">
                                    <span class="icon icon--min">ಠ╭╮ಠ</span> 
                                     ${msg}
                                </a>
                                <button type="submit" class="btn btn--right">Sign in  </button>

                            </div>
                        </form>
                    </div>
                    
                   
                </div>
            </div>

        </div>



        <!-- FORM MAIN -->

        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>     

        <!-- scripts -->
        <script src="assets/particles/js/particles.js"></script>
        <script src="assets/particles/js/app.js"></script>

        <!-- stats.js -->
        <script src="assets/particles/js/lib/stats.js"></script>
        <script>
            var count_particles, stats, update;
            stats = new Stats;
            stats.setMode(0);
            stats.domElement.style.position = 'absolute';
            stats.domElement.style.left = '0px';
            stats.domElement.style.top = '0px';
            document.body.appendChild(stats.domElement);
            count_particles = document.querySelector('.js-count-particles');
            update = function () {
                stats.begin();
                stats.end();
                if (window.pJSDom[0].pJS.particles && window.pJSDom[0].pJS.particles.array) {
                    count_particles.innerText = window.pJSDom[0].pJS.particles.array.length;
                }
                requestAnimationFrame(update);
            };
            requestAnimationFrame(update);
        </script>


    </body>
</html>
