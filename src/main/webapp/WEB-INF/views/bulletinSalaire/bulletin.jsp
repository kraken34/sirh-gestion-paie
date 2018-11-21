<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="../head.jsp?title=List Bulletin"></c:import>
<body>
	<main class="container">
	<h1>Bulletins de salaire</h1>
	<br>
	<h2>Salaire</h2>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">Rubriques</th>
				<th scope="col">Base</th>
				<th scope="col">Taux Salarial</th>
				<th scope="col">Montant salarial</th>
				<th scope="col">Taux Patronal</th>
				<th scope="col">Cot. Patronal</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td>Salaire de base</td>
				<td>${bulletin.remunerationEmploye.grade.nbHeuresBase}</td>
				<td>${bulletin.remunerationEmploye.grade.tauxBase}</td>
				<td>${calculeSalaire.salaireDeBase}</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Prime Except.</td>
				<td></td>
				<td></td>
				<td>${bulletin.primeExceptionnelle}</td>
				<td></td>
				<td></td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td>Salaire Brut</td>
				<td></td>
				<td></td>
				<td>${calculeSalaire.salaireBrut}</td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<h2>cotisation</h2>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">Rubriques</th>
				<th scope="col">Base</th>
				<th scope="col">Taux Salarial</th>
				<th scope="col">Montant salarial</th>
				<th scope="col">Taux Patronal</th>
				<th scope="col">Cot. Patronal</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach
				items="${bulletin.remunerationEmploye.profilRemuneration.cotisations}"
				var="cotisation">
				<c:if test="${!cotisation.imposable}">
					<tr>
						<td>${cotisation.code} ${cotisation.libelle}</td>
						<td>${calculeSalaire.salaireBrut}</td>
						<td>${cotisation.tauxSalarial}</td>
						<td>${calculeCotisation.get(cotisation.id).get(0)}</td>
						<td>${cotisation.tauxPatronal}</td>
						<td>${calculeCotisation.get(cotisation.id).get(1)}</td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td>Total Retenue</td>
				<td></td>
				<td></td>
				<td>${calculeSalaire.totalRetenueSalarial}</td>
				<td></td>
				<td>${calculeSalaire.totalCotisationsPatronales}</td>
			</tr>
		</tbody>
	</table>
	<h2>Net Imposable :${calculeSalaire.netImposable}</h2>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">Rubriques</th>
				<th scope="col">Base</th>
				<th scope="col">Taux Salarial</th>
				<th scope="col">Montant salarial</th>
				<th scope="col">Taux Patronal</th>
				<th scope="col">Cot. Patronal</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach
				items="${bulletin.remunerationEmploye.profilRemuneration.cotisations}"
				var="cotisation">
				<c:if test="${cotisation.imposable}">
					<tr>
						<td>${cotisation.code} ${cotisation.libelle}</td>
						<td>${calculeSalaire.salaireBrut}</td>
						<td>${cotisation.tauxSalarial}</td>
						<td>${calculeCotisation.get(cotisation.id).get(0)}</td>
						<td>${cotisation.tauxPatronal}</td>
						<td>${calculeCotisation.get(cotisation.id).get(1)}</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<h2>NET A PAYER:${calculeSalaire.netAPayer}</h2>
	</main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>