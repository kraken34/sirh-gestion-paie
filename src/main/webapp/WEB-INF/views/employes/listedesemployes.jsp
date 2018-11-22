<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>liste des employés</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="collapse navbar-collapse" id="maNavBar">
			<ul class="nav navbar-nav">
				<li><a href='<c:url value="/mvc/employes/creer/"></c:url>'><span
						aria-hidden="true"></span> Employés</a></li>
				<li><a href='<c:url value="/mvc/bulletins/creer/"></c:url>'><span
					aria-hidden="true"></span> Bulletin </a></li>
		</ul>
		</div>
	</nav>
	
	<h1>Liste des employés</h1>
	<table class="table table-striped ">
		<thead>
			<tr>
				<th>Date/Heure création</th>

				<th>Matricule</th>

				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${employes}" var="employe">
    <tr>
						<td>${employe.matricule}</td>
						<td>${employe.entreprise.denomination}</td>
						<td>${employe.profilRemuneration.code}</td>
						<td>${employe.grade.code}</td>
						</tr>
					</c:forEach>
		</tbody>
	</table>
</body>

</html>