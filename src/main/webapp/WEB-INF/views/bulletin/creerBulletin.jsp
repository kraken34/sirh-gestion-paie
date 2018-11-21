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

	<h1>Créer Bulletin</h1>
 
 
	<form:form method="POST" modelAttribute="bulletin">

		<form:select path="periode.id" items="${listePeriode}" itemValue="id" itemLabel="dateDebutFin"></form:select>
		<form:select path="remunerationEmploye.id" items="${listeEmployes}" itemValue="id" itemLabel="matricule"></form:select>
		<form:input path="primeExceptionnelle" placeholder="prime exceptionnelle"></form:input>
		
		<form:input id="buttonForm" path="" type="submit" value="Créer"></form:input>	

	</form:form>

</body>
</html>