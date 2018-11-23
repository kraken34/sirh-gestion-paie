<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/head.jsp"></c:import>
<body>
	<div class="container-fluid">
	<h1>Lister les Bulletins</h1>				
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Date/heure création</th>
					<th scope="col">Période</th>
					<th scope="col">Matricule</th>
					<th scope="col">Salaire brut</th>
					<th scope="col">Net imposable</th>
					<th scope="col">Net a payer</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bulletin" items="${mapBulletins}">
					<tr>
						<td><c:out value="${bulletin.key.remunerationEmploye.creation}"/></td>
						<td><c:out value="${bulletin.key.periode}"/></td>
						<td><c:out value="${bulletin.key.remunerationEmploye.matricule}"/></td>
						<td><c:out value="${bulletin.value.salaireBrut}"/></td>
						<td><c:out value="${bulletin.value.netImposable}"/></td>
						<td><c:out value="${bulletin.value.netAPayer}"/></td>
						<td><a class="btn btn-primary" href="afficher?idBulletin=${bulletin.key.id}">Afficher</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-right">
			<a class="btn btn-primary" href="creer">Créer Bulletin</a>
		</div>
	</div>
</body>
</html>