<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PAIE</title>
</head>
<body>
	<h1>BULLETIN DE SALAIRE</h1>

	<h2>Période :</h2>
	<c:out value="${bulletin.periode.dateAffichee}" />

<h2>Entreprise</h2>
<c:out value="${bulletin.remunerationEmploye.entreprise.denomination}" />

<div>Siret : <c:out value="${bulletin.remunerationEmploye.entreprise.siret}" /></div>
<h2>Matricule</h2>
<c:out value="${bulletin.remunerationEmploye.matricule}" />
	<h2>Salaire</h2>
	<table>
		<tr>
			<td>Rubriques</td>
			<td>Base</td>
			<td>Taux Salarial</td>
			<td>Montant Salarial</td>
			<td>Taux patronal</td>
			<td>Cot. patronales</td>
		</tr>
		<tr>
			<td>Salaire de base</td>
			<td><c:out
					value="${bulletin.remunerationEmploye.grade.nbHeuresBase}" /></td>
		</tr>
		<tr>
			<td>Prime Exceptionnelle</td>
			<td><c:out value="${bulletin.primeExceptionnelle}" /></td>
		</tr>
		<tr>

		</tr>
	</table>

	<p>Cotisations</p>
	<ul>
		<c:forEach var="cotisation"
			items="${bulletin.remunerationEmploye.profilRemuneration.cotisations}">
 <c:if test = "${cotisation.imposable==false}">
        <table>
				<tr>
					<td><c:out value="${cotisation.code}" /> <c:out
							value="${cotisation.libelle}" /></td>
					<td><c:out value="${Brut.salaireBrut}" /></td>
					<td><c:out value="${cotisation.tauxSalarial}" /></td>
				</tr>

				

			</table>
      </c:if>
			

		</c:forEach>
	</ul>
				<tr>
				<td>Total Retenue</td>
				<td></td>
				<td></td>
				<td><c:out value="${Brut.totalRetenueSalarial}" /></td>
				<td><c:out value="${Brut.totalCotisationsPatronales}" /></td>
				</tr>

	<p>NET Imposable : <c:out value="${Brut.netImposable}" /></p>
	<c:forEach var="cotisation"
			items="${bulletin.remunerationEmploye.profilRemuneration.cotisations}">
 <c:if test = "${cotisation.imposable==true}">
        <table>
				<tr>
					<td><c:out value="${cotisation.code}" /> <c:out value="${cotisation.libelle}" /></td>
					<td><c:out value="${Brut.salaireBrut}" /></td>
					<td><c:out value="${cotisation.tauxSalarial}" /></td>
				</tr>

				

			</table>
      </c:if>
			

		</c:forEach>
	<p>NET A PAYER : <c:out value="${Brut.netAPayer}" /></p>
</body>
</html>