<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un employé</title>
<%@ include file="../menu/head/head.jsp"%>

</head>
<body>
	<%@ include file="../menu/menu.jsp"%>

	<h1>Créer Employe</h1>

	<form:form method="POST" modelAttribute="employe">

		<form:input path="matricule" placeholder="matricule"></form:input>
		<form:select path="entreprise.id" items="${listeEntreprises}"
			itemValue="id" itemLabel="denomination"></form:select>
		<form:select path="profilRemuneration.id" items="${listeProfils}"
			itemValue="id" itemLabel="code"></form:select>
		<form:select path="grade.id" items="${listeGrades}" itemValue="id"
			itemLabel="code"></form:select>
		<form:input id="buttonForm" path="" type="submit" value="Ajouter"></form:input>

	</form:form>

	<a href="../../index.html">Retour</a>

</body>
</html>