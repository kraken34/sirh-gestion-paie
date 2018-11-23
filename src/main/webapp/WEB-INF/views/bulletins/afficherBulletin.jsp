<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/head.jsp"></c:import>
<body>
	<div class="container-fluid">
	<h1>Bulletin de salaire</h1>
		<div>
			<h2>Période</h2>
			<div>${bulletin.periode}</div>
		</div>
		<div>
			<h2>Entreprise</h2>
			<div>${bulletin.remunerationEmploye.entreprise.denomination}</div>
			<div>Siret : ${bulletin.remunerationEmploye.entreprise.siret}</div>
		</div>
		<div>
			<h2>Matricule : ${bulletin.remunerationEmploye.matricule}</h2>
		</div>	
		
		<h2>Salaire</h2>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Rubrique</th>
					<th scope="col">Base</th>
					<th scope="col">Taux salarial</th>
					<th scope="col">Montant salarial</th>
					<th scope="col">Taux patronal</th>
					<th scope="col">Cot. patronales</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				 	<th scope="row">Salaire de base</th>
				 	<td>${bulletin.remunerationEmploye.grade.nbHeuresBase}</td>
				 	<td>${bulletin.remunerationEmploye.grade.tauxBase}</td>
				 	<td>${calcul.salaireDeBase}</td>
				 	<td></td>
				 	<td></td>
				</tr>
				<tr>
				 	<th scope="row">Prime Except.</th>
				 	<td></td>
				 	<td></td>
				 	<td>${bulletin.primeExceptionnelle}</td>
				 	<td></td>
				 	<td></td>
				</tr>
				<tr>
				 	<th scope="row">Salaire Brut</th>
				 	<td></td>
				 	<td></td>
				 	<td>${calcul.salaireBrut}</td>
				 	<td></td>
				 	<td></td>
				</tr>
			</tbody>
		</table>
		
		<h2>Cotisations</h2>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Rubrique</th>
					<th scope="col">Base</th>
					<th scope="col">Taux salarial</th>
					<th scope="col">Montant salarial</th>
					<th scope="col">Taux patronal</th>
					<th scope="col">Cot. patronales</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cotisation" items="${bulletin.remunerationEmploye.profilRemuneration.cotisations}">
					<c:if test="${cotisation.imposable==false}">
						<tr>
							<td>${cotisation.code} ${cotisation.libelle}</td>
							<td>${calcul.salaireBrut}</td>
							<td>${cotisation.tauxSalarial}</td>
							<td></td>
							<td>${cotisation.tauxPatronal}</td>
							<td></td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
				 	<th scope="row">Total Retenues</th>
				 	<td></td>
				 	<td></td>
				 	<td>${calcul.totalRetenueSalarial}</td>
				 	<td></td>
				 	<td>${calcul.totalCotisationsPatronales}</td>
				</tr>
			</tbody>
		</table>
		
		<h2>Net Imposable : <c:out value="${calcul.netImposable}"/></h2>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Rubrique</th>
					<th scope="col">Base</th>
					<th scope="col">Taux salarial</th>
					<th scope="col">Montant salarial</th>
					<th scope="col">Taux patronal</th>
					<th scope="col">Cot. patronales</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cotisation" items="${bulletin.remunerationEmploye.profilRemuneration.cotisations}">
					<c:if test="${cotisation.imposable==true}">
						<tr>
							<td>${cotisation.code} ${cotisation.libelle}</td>
							<td>${calcul.salaireBrut}</td>
							<td>${cotisation.tauxSalarial}</td>
							<td></td>
							<td>${cotisation.tauxPatronal}</td>
							<td></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>