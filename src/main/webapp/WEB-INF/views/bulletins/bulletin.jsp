<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bulletin</title>
</head>
<body>
	<c:import url="../head.jsp"></c:import>
	<header>
		<c:import url="../menu.jsp"></c:import>
	</header>
	<h1>Bulletin</h1>

	<h4>Période</h4>
	<p>${bulletin.periode.getDateDebutDateFin()}</p>

	<h4>Entreprise</h4>
	<p>${bulletin.remunerationEmploye.entreprise.denomination}</p>

	<h4>SIRET</h4>
	<p>${bulletin.remunerationEmploye.entreprise.siret}</p>

	<h4>Matricule</h4>
	<p>${bulletin.remunerationEmploye.matricule}</p>
	<p>${bulletin.getDateFormat("dd/MM/yyyy hh:mm:ss")}</p>
	<main class="container">
	<table class="table table-bordered table-striped table-sm text-center">
		<tr>
			<th>Nom :</th>
			<th>Prénom :</th>
			<th>Date de naissance :</th>
			<th>Adresse mail :</th>
		</tr>
		<tr>
			<td>${collegue[0].nom}</td>
			<td>${collegue[0].prenom}</td>
			<td>${collegue[0].dateNaissance}</td>
			<td>${collegue[0].email}</td>

		</tr>
	</table>
	<table class="table table-bordered table-striped table-sm text-center">
		<tr>
			<th>Adresse postale :</th>
			<th>Département au sein de l'entreprise :</th>
			<th>Matricule employé :</th>
		</tr>
		<tr>
			<td>${collegue[0].adresse}</td>
			<td>${collegue[0].departement}</td>
			<td>${collegue[0].matricule}</td>
		</tr>
	</table>
	</main>
	<h4>Salaire</h4>
	<table>
		<thead class="text-center">
			<tr>
				<th>Rubrique</th>
				<th>Base</th>
				<th>Taux salarial</th>
				<th>Montant salarial</th>
				<th>Taux patronal</th>
				<th>Cotisations patronales</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Salaire de base</th>
				<td>${bulletin.remunerationEmploye.grade.nbHeuresBase}</td>
				<td>${bulletin.remunerationEmploye.grade.tauxBase}</td>
				<td>${calculeSalaires.salaireDeBase}</td>
			</tr>
			<%-- <h4>Cotisations</h4>
			<tr>
				<c:forEach items="{bulletin.remunerationEmploye.profilRemuneration.cotisations}"
					var="cotisation">
					<c:if test="${!cotisation.imposable}">
					<td></td>
					</c:if>
				</c:forEach>
			</tr> --%>
			<tr>
				<th>Prime Exceptionnelle</th>
				<td></td>
				<td></td>
				<td>${bulletin.primeExceptionnelle}</td>
			</tr>
			<tr>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>Salaire Brut</th>
				<td></td>
				<td></td>
				<td>${calculeSalaires.salaireBrut}</td>
			</tr>

		</tbody>
	</table>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>