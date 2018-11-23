<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="../head.jsp?title=List Employer"></c:import>
<body>
	<main class="container"> <c:import url="../menu.jsp"></c:import>
	<h1>Liste des employés</h1>
	<a href="<c:url value="creer"/>"><button class="btn btn-primary">Ajouter
			un employer</button></a> <br>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th scope="col">Date/Heur création</th>
				<th scope="col">Matricule</th>
				<th scope="col">Grade</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listeEmploye}" var="employe">
				<tr>
					<td>${employe.getDateFormat()}</td>
					<td>${employe.matricule}</td>
					<td>${employe.grade.code}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>