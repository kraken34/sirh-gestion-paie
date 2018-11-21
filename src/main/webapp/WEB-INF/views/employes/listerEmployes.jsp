<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../../head.jsp"%>
</head>

<body>
	<h1>Liste des Employés</h1>

	<header>
		<%@include file="../../menu.jsp"%>
	</header>

	<div class="container">
		<div class="row justify-content-around align-items-center">
			<div class="col-offset-9 col-3 text-center">
				<a href='<c:url value="/mvc/employes/creer"></c:url>'
					class="btn btn-primary">Ajouter un employé</a>
			</div>
		</div>
	</div>

	<br />
	
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th scope="col">Date/heure création</th>
				<th scope="col">Matricule</th>
				<th scope="col">Grade</th>
			</tr>
		</thead>

		<c:if test="${not empty listeEmployes}">
			<tbody>

				<c:forEach items="${listeEmployes}" var="employeCourant">
					<tr>
						<td><c:out value="${employeCourant.dateCreation}"></c:out></td>
						<td><c:out value="${employeCourant.matricule}"></c:out></td>
						<td><c:out value="${employeCourant.grade.code}"></c:out></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</c:if>
	</table>

	<%@include file="../../finBody.jsp"%>
</body>
</html>
