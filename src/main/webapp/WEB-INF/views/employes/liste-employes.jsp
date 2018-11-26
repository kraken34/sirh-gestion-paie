<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des employés</title>
</head>
<body>
<header>
<c:import url="../menu.jsp"></c:import>
</header>
	<c:import url="../head.jsp"></c:import>
	<main class="container">
	<table class="table table-bordered table-striped table-sm text-center">
		<caption>
			<h4>Liste des employés</h4>
		</caption>
		<c:forEach items="${listeEmployes}" var="employe">
			<thead class="text-center">
				<tr>
					<th rowspan="2"><a href="edit?id=${employe.id}">${employe.matricule}</a></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Date Creation</td>
					<td>${employe.getDateFormat("dd/MM/yyyy hh:mm:ss")}</td>
				</tr>
				<tr>
					<td>Matricule</td>
					<td><a href="edit?id=${employe.id}">${employe.matricule}</a></td>
				</tr>
				<tr>
					<td>Grade</td>
					<td>${employe.grade.code}</td>
				</tr>
				<tr>
				<td>poste</td>
				<td>${employe.profilRemuneration.code}</td>
				</tr>
				<tr>
					<td>Entreprise</td>
					<td>${employe.entreprise.denomination}</td>
				</tr>
			</tbody>
			<br />
		</c:forEach>
	</table>
	</main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>