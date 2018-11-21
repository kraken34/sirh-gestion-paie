<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="../head.jsp?title=List Bulletin"></c:import>
<body>
	<main class="container">
	<h1>Liste des Bulletins	</h1>
	<a href="<c:url value="creer"/>"><button class="btn btn-primary">Crer un nouveau bulletin</button></a>
	<br>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">Date/Heur création</th>
				<th scope="col">Période</th>
				<th scope="col">Matricule</th>
				<th scope="col">Salaire brute</th>
				<th scope="col">Net Imposable</th>
				<th scope="col">Net A Payer</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listeBulletin}" var="bulletin">
				<tr>
					<td>${bulletin.key.getDateFormat()}</td>
					<td>${bulletin.key.periode.getPeriodeString()}</td>
					<td>${bulletin.key.remunerationEmploye.getMatricule()}</td>
					<td>${bulletin.value.getSalaireBrut()}</td>
					<td>${bulletin.value.getNetImposable()}</td>
					<td>${bulletin.value.getNetAPayer()}</td>
					<td><a href="<c:url value="find?key=${bulletin.key.getId()}"/>"><button class="btn btn-primary">Visualiser</button></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>