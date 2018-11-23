<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/head.jsp"></c:import>
<body>
	<div class="container-fluid">
	<h1>Lister les Employés</h1>				
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Date/heure création</th>
					<th scope="col">Matricule</th>
					<th scope="col">Grade</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employe" items="${listeEmploye}">
					<tr>
						<td><c:out value="${employe.creation}"/></td>
						<td><c:out value="${employe.matricule}"/></td>
						<td><c:out value="${employe.grade.code}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-right">
			<a class="btn btn-primary" href="creer">Créer Employé</a>
		</div>
	</div>
</body>
</html>