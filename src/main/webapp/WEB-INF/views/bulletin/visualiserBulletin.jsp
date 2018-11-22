<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Liste des bulletins</title>
	<link rel="stylesheet" type="text/css" href="/css/design.css">
</head>
<body>
	<%@include file="../menu/menu.jsp" %>
	<h1>Bulletin de salaire</h1>
	
	<div id="infos">
		<br>Entreprise
		<br><c:out value="${bulletin.remunerationEmploye.entreprise.denomination}"/>
		<br>SIRET : <c:out value="${bulletin.remunerationEmploye.entreprise.siret}"/>
		<br><br>Période
		<br><c:out value="${bulletin.periode.dateDebut}"/> --> <c:out value="${bulletin.periode.dateFin}"/>
		<br><br>Matricule : <c:out value="${bulletin.remunerationEmploye.matricule}"/>
	</div>
	
	<div id="salaire" class="tableau">
		<br>Salaire
		<table>
			<tr><th>Rubrique</th><th>Base</th><th>Taux salarial</th><th>Montant Salarial</th><th>Taux patronal</th><th>Cot. patronales</th></tr>
			<tr><th>Salaire de base</th><th><c:out value="${bulletin.remunerationEmploye.grade.nbHeuresBase}"/></th>
			<th><c:out value="${bulletin.remunerationEmploye.grade.tauxBase}"/></th><th><c:out value="${resCalcRem.salaireDeBase}"/></th><th></th><th></th></tr>
			<tr><th>Prime except.</th><th></th><th></th><th><c:out value="${bulletin.primeExceptionnelle}"/></th><th></th><th></th></tr>
			<tr><th></th><th></th><th></th><th></th><th></th><th></th></tr>
			<tr><th>Salaire brut</th><th></th><th></th><th><c:out value="${resCalcRem.salaireBrut}"/></th><th></th><th></th></tr>
		</table>
	</div>
	
	<div id="cotisations">
		<br>Cotisations
		<table class="tableau" class="tableau">
			<tr><th>Rubrique</th><th>Base</th><th>Taux salarial</th><th>Montant Salarial</th><th>Taux patronal</th><th>Cot. patronales</th></tr>
			<c:forEach items="${cotNonImposables}" var="cotisation">
				<tr><th><c:out value="${cotisation.code}"/> <c:out value="${cotisation.libelle}"/></th>
				<th><c:out value="${resCalcRem.salaireBrut}"/></th>
				<th><c:out value="${cotisation.tauxSalarial}"/></th>
				<th><c:out value="${montantSalarial.get(cotisation)}"/></th>
				<th><c:out value="${cotisation.tauxPatronal}"/></th>
				<th><c:out value="${cotisationsPatronales.get(cotisation)}"/></th></tr>
			</c:forEach>
			<tr><th>Total retenue</th><th></th><th></th><th><c:out value="${resCalcRem.totalRetenueSalarial}"/></th><th></th>
			<th><c:out value="${resCalcRem.totalCotisationsPatronales}"/></th></tr>
		</table>
	</div>
	
	<div id="netimposable" class="tableau">
		<br>Net imposable : <c:out value="${resCalcRem.netImposable}"/>
		<table class="tableau">
			<c:forEach items="${cotImposables}" var="cotisation">
				<tr><th><c:out value="${cotisation.code}"/> <c:out value="${cotisation.libelle}"/></th>
				<th><c:out value="${resCalcRem.salaireBrut}"/></th>
				<th><c:out value="${cotisation.tauxSalarial}"/></th>
				<th><c:out value="${montantSalarial.get(cotisation)}"/></th>
				<th><c:out value="${cotisation.tauxPatronal}"/></th>
				<th><c:out value="${cotisationsPatronales.get(cotisation)}"/></th></tr>
			</c:forEach>
		</table>
	</div>
	
	<div id="netapayer">
		<br><br>Net à payer : <c:out value="${resCalcRem.netAPayer}"/>
	</div>
</body>
</html>