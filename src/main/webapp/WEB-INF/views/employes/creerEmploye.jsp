<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un employé</title>
</head>
<body>
	<%@include file="../menu/menu.jsp" %>
	<h1>Créer un employé</h1>
	<form:form method="POST" modelAttribute="employe">
	<form:input path="matricule" placeholder="matricule"></form:input>
	<form:select path="entreprise.id" items="${listeEntreprises}" itemValue="id" itemLabel="denomination"></form:select>
	<form:select path="profilRemuneration.id" items="${listeProfiles}" itemValue="id" itemLabel="code"></form:select>
	<form:select path="grade.id" items="${listeGrades}" itemValue="id" itemLabel="code"></form:select>
	<form:input path="" type="submit" value="Ajouter"></form:input>
</form:form>
</body>
</html>