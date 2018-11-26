<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <meta charset="UTF-8">
        <title>Paie - App</title>
        <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    </head>
    <body class="container">        

        <h1>Connexion</h1>

        <!-- Spring Security s'attend aux paramètres "username" et "password" -->
        <form method="post">
            <input name="username">
            <input type="password" name="password">
            <input type="submit" value="Se connecter">
            <!-- génération du Token CSRF -->
            <sec:csrfInput/>
        </form>

        <!-- en cas d'erreur un paramètre "error" est créé par Spring Security -->
        <c:if test="${param.error !=null}">
            Erreur d'authentification
        </c:if>
    </body>
</html>