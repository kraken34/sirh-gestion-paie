<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<%@ include file="../menu/head/head.jsp"%>

</head>
<body>
	<%@ include file="../menu/menu.jsp"%>
	<h1>SIRH - Gestion de la paie</h1>

	<div class="bandeauBouton">
		<a href="employes/creer" class="button">Créer un employé</a>
		<a href="employes/lister" class="button">Lister les employés</a>
		<a href="bulletin/creer" class="button">Créer un bulletin</a>
		<a href="bulletin/lister" class="button">Lister un bulletin</a>
	</div>
</body>
</html>