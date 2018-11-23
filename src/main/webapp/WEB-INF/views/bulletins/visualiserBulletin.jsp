<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../../head.jsp"%>
</head>

<body>
	<h1>Bulletin De Salaire</h1>

	<header>
		<%@include file="../../menu.jsp"%>
	</header>

	<div class="container">
		<div class="row justify-content-around align-items-center">
			<div class="col-offset-9 col-3 text-center">
				<p><h2>Période : </h2><br/>
					
				</p>
			</div>
		</div>
	</div>

	<br />
	
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th scope="col">Date/heure création</th>
				<th scope="col">Période</th>
				<th scope="col">Matricule</th>
				<th scope="col">Salaire brut</th>				
				<th scope="col">Net imposable</th>			
				<th scope="col">Net à payer</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>



			<tbody>

				<c:forEach items="${listeCalculSurBulletin}" var="calculCourant">
					<tr>
						<td><c:out value="${calculCourant.bulletin.dateCreation}"></c:out></td>
						<td><c:out value="${calculCourant.bulletin.periode.libelle}"></c:out></td>
						<td><c:out value="${calculCourant.bulletin.remunerationEmploye.matricule}"></c:out></td>
						<td><c:out value="${calculCourant.remuneration.salaireBrut}"></c:out></td>
						<td><c:out value="${calculCourant.remuneration.netImposable}"></c:out></td>
						<td><c:out value="${calculCourant.remuneration.netAPayer}"></c:out></td>
						<td><a href="creationReussie">Visualiser</a></td>
					</tr>
				</c:forEach>
				
			</tbody>
	
	</table>

	<%@include file="../../finBody.jsp"%>
</body>
</html>
