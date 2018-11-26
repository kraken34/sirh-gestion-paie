<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Créer Bulletin</title>
</head>
<body>
	<c:import url="../head.jsp"></c:import>
	<header>
	<c:import url="../menu.jsp"></c:import>
	</header>
	<h1>Créer Bulletin</h1>
	<main class="container"> <form:form method="post"
		modelAttribute="bulletin">
		<div class="form-group">
			<label for="primeExceptionnelle">Prime exceptionnelle</label>
			<form:input path="primeExceptionnelle" />
		</div>
		<div class="form-group">
			<form:select cssClass="form-control" path="periode.id"
				items="${periode}" itemValue="id" itemLabel="dateDebutDateFin"></form:select>
		</div>
		<div class="form-group">
			<form:select cssClass="form-control" path="remunerationEmploye.id"
				items="${listeRemunerationEmploye}" itemValue="id" itemLabel="matricule"></form:select>
		</div>
		<form:button class="btn btn-primary">Enregistrer</form:button>
	</form:form> </main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>