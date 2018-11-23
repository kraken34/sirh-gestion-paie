

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Paie - App</title>
        <link rel="stylesheet" href='<c:url value="/bootstrap-3.3.7/css/bootstrap.min.css"></c:url>'>
    </head>

   
    <body class="container">        
        ...
        <form method="post">
            <input name="username">
            <input name="password">
            <input type="submit" value="Se connecter">

            <!-- génération du Token CSRF -->
            
            
            
            <sec:csrfInput/>
        </form>
        ...
    </body>
   
    </body>
</html>
