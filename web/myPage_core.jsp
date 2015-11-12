<%-- 
    Document   : myPage_core
    Created on : 19/10/2015, 03:09:36 PM
    Author     : FGALVIS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page import="java.util.List" %>
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="/img/favicon.ico" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/specific.css" />
        <title>FG-Soft</title>
    </head>
    <body>
        <div class="divider col-xs-12"></div>
        <h4 class="text-center">EJERCICIOS DATOS, TABLAS, ARREGLOS Y MATRICES</h4>
        <h5 class="text-center">INTRODUCCION A LA ING. DE SISTEMAS - Ing. German Alvarez - INGENIERIA DE SISTEMAS - SEMESTRE 1</h5>
        <div class="container">
            <div class="card card-container">
                <img id="profile-img" class="profile-img-card" src="//carrerasuniversitarias.com.co/logos/original/logo-universidad-simon-bolivar.png" />
                <p id="profile-name" class="profile-name-card"></p>
                <h5 class="text-center">Ingrese los Nombres de los Paises que desea evaluar</h5>
                <form class="form-signin" method="POST" action="myClass_Main">
                    <span id="reauth-email" class="reauth-email"></span>
                    <input type="hidden" id="optionValue" name="optionValue" value="2"/>
                        <%
                        out.println(request.getAttribute("htmlres"));
                        %>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Aceptar</button>
                </form><!-- /form -->
            </div><!-- /card-container -->
        </div><!-- /container -->
        <div class="col-xs-12 divider"></div>
        <h5 class="text-center">Copyrigth: Favio Arturo Galvis - Octubre/2015</h5>
        <h6 class="text-center">201521268405</h6>
        <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>