<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Créer Employe</title>
</head>
<body>
	<c:import url="../head.jsp"></c:import>
	<header>
	<c:import url="../menu.jsp"></c:import>
	</header>
	<h1>Créer Employe</h1>
	<main class="container"> <form:form method="post"
		modelAttribute="employe">
		<div class="form-group">
			<form:select cssClass="form-control" path="matricule">
				<form:option value="Veuillez sélectionner votre matricule ..."></form:option>
				<form:options items="${listeCollegues}" itemValue="matricule" itemLabel="matricule"/>
			</form:select>
		</div>
		<div class="form-group">
			<form:select cssClass="form-control" path="entreprise.id"
				items="${listeEntreprises}" itemValue="id" itemLabel="denomination"></form:select>
		</div>
		<div class="form-group">
			<form:select cssClass="form-control" path="profilRemuneration.id"
				items="${listeProfilRemuneration}" itemValue="id" itemLabel="code"></form:select>
		</div>
		<div class="form-group">
			<form:select cssClass="form-control" path="grade.id" items="${listeGrades}" itemValue="id"
				itemLabel="code"></form:select>
		</div>
		<form:button class="btn btn-primary">Enregistrer</form:button>
	</form:form> </main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>