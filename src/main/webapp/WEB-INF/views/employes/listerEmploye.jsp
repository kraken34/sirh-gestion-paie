<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.time.LocalDate"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lister des employés</title>
<%@ include file="../menu/head/head.jsp"%>
</head>
<body>
	<%@ include file="../menu/menu.jsp"%>

	<h1>Lister employés</h1>

<div id="tableau">
	<table>
	<thead>
		<tr>
			<th>Date et heure de création</th>
			<th>Matricule</th>
			<th>Grade</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listeEmployes }" var="employe">
			<tr>
				<td><c:out value="${employe.date}"></c:out></td>
				<td><c:out value="${employe.matricule}"></c:out></td>
				<td><c:out value="${employe.grade.code}"></c:out></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>