<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lister des bulletins</title>
<%@ include file="../menu/head/head.jsp"%>

</head>
<body>
	<%@ include file="../menu/menu.jsp"%>

	<h1>Lister bulletins</h1>

	<div class="callToAction">
		<a href="" class="button">Créer un nouveau bulletin</a>
	</div>

	<div id="tableauBulletin">
		<table>
		<thead>
			<tr>
				<th>Date et heure de création</th>
				<th>Période</th>
				<th>Matricule</th>
				<th>Salaire brut</th>
				<th>Net imposable</th>
				<th>Net à payer</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listeBulletin }" var="bulletin">
				<tr border-collapse>
					<td><c:out value="${bulletin.date}" /></td>
					<td><c:out value="${bulletin.periode.dateDebut}" />--> <c:out value="${bulletin.periode.dateFin}" /></td>
					<td><c:out value="${bulletin.remunerationEmploye.matricule}" /></td>
					<td><c:out value="${salairesBrut.get(bulletin)}" /></td>
					<td><c:out value="${salairesNet.get(bulletin)}" /></td>
					<td><c:out value="${salairesNetImposable.get(bulletin)}" /></td>
					<td><a href="visualiser?idBulletin=${bulletin.id }">Actions</a></td>
				</tr>

			</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>