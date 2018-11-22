<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualiser les bulletins</title>
<%@ include file="../menu/head/head.jsp"%>

</head>
<body>
	<%@ include file="../menu/menu.jsp"%>
	<h1>Visualiser Bulletin</h1>

	<div class="periode">
		<p class="entete">Période</p>
		<c:out value="${bulletin.periode.dateDebut}" />
		-->
		<c:out value="${bulletin.periode.dateFin}" />
	</div>


	<div class="entreprise">
		<p class="entete">Entreprise</p>
		<div class="infoEntreprise">
			<c:out
				value="${bulletin.remunerationEmploye.entreprise.denomination}" />
			<br>
			<c:out value="${bulletin.remunerationEmploye.entreprise.siret}" />
			<br> <br>
			<p class="entete">Matricule</p>
			<c:out value="${bulletin.remunerationEmploye.matricule}" />
		</div>
	</div>


	<div id="tableau">
		<p class="entete">Salaire</p>
		<br>
		<table>
			<thead>
				<tr>
					<th>Rubrique</th>
					<th>Base</th>
					<th>Taux salarial</th>
					<th>Montant salarial</th>
					<th>Taux patronal</th>
					<th>Cot. patronales</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Salaire de base</td>
					<td><c:out value="${calSalaireBase}"></c:out></td>
					<td><c:out value="${cotisation.tauxSalarial}"></c:out></td>
					<td><c:out value="${calCotPatronales }"></c:out></td>
					<td><c:out value="${cotisation.tauxPatronal }"></c:out></td>
					<td><c:out value="${calCotPatronales }"></c:out></td>
				</tr>
			</tbody>
		</table>
	</div>


	<!-- 

	<div id="tableauBulletin">
		<table>
			<thead>
				<tr>
					<th>Rubrique</th>
					<th>Base</th>
					<th>Taux salarial</th>
					<th>Montant salarial</th>
					<th>Taux patronal</th>
					<th>Cot. patronales</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>


	<div id="tableauBulletin">
		<table>
			<thead>
				<tr>
					<th>Rubrique</th>
					<th>Base</th>
					<th>Taux salarial</th>
					<th>Montant salarial</th>
					<th>Taux patronal</th>
					<th>Cot. patronales</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	-->
</body>
</html>