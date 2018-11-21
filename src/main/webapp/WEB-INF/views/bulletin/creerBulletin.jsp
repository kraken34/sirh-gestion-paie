<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un bulletin</title>
</head>
<body>
	<%@include file="../menu/menu.jsp" %>
<form:form method="POST" modelAttribute="bulletin">
	<form:select path="periode.id" items="${listePeriodes}" itemValue="id" itemLabel="DebFin"></form:select>
	<form:select path="remunerationEmploye.id" items="${listeEmployes}" itemValue="id" itemLabel="matricule"></form:select>
	<form:input path="primeExceptionnelle" placeholder="prime"></form:input>
	<form:input path="" type="submit" value="Ajouter"></form:input>
</form:form>
</body>
</html>