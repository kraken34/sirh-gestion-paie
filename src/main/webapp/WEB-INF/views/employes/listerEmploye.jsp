<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des employés</title>
</head>
<body>
	<%@include file="../menu/menu.jsp"%>
	<h1>Liste des employés</h1>
	<table>
		<tr><th>Date/heure création</th><th>Matricule</th><th>Grade</th></tr>
		<c:forEach items="${listEmployes}" var="employe">
			<tr><th><c:out value="${employe.date}"/></th><th><c:out value="${employe.matricule}"/></th><th><c:out value="${employe.grade.code}"/></th></tr>
		</c:forEach>
	</table>
</body>
</html>