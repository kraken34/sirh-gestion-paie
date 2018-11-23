<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/head.jsp"></c:import>
<body>
	<div class="container-fluid">
	<h1>Lister les Matricules</h1>
	<h2>Veuillez utiliser un matricule existant ci-desous !</h2>			
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Photo</th>
					<th scope="col">Nom</th>
					<th scope="col">Prenom</th>
					<th scope="col">Matricule</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employe" items="${listeCollegues}">
					<tr>
						<td><img src="${employe.photo}"></td>
						<td>${employe.nom}</td>
						<td>${employe.prenom}</td>
						<td>${employe.matricule}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>