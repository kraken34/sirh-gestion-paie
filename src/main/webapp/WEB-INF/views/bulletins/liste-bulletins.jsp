<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des bulletins</title>
</head>
<body>
	<header>
		<c:import url="../menu.jsp"></c:import>
	</header>
	<c:import url="../head.jsp"></c:import>
	<main class="container">
	<table class="table table-bordered table-striped table-sm text-center">
		<caption>
			<h4>Bulletins de salaire</h4>
		</caption>

		<thead class="text-center">
			<tr>
				<th>Date Creation</th>
				<th>Matricule</th>
				<th>Période</th>
				<th>Salaire brut</th>
				<th>Net imposable</th>
				<th>Net à payer</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listeBulletinsCalculs}" var="bulletin">
				<tr>
					<td>${bulletin.key.getDateFormat("dd/MM/yyyy hh:mm:ss")}</td>
					<td>${bulletin.key.remunerationEmploye.matricule}</td>
					<td>${bulletin.key.periode.getDateDebutDateFin()}</td>
					<td>${bulletin.value.getSalaireBrut()}</td>
					<td>${bulletin.value.getNetImposable()}</td>
					<td>${bulletin.value.getNetAPayer()}</td>
					<td><a href="<c:url value="bulletin?id=${bulletin.key.id}"/>">Visualiser</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>