<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>La liste des employés</h1>

<table class="table table-striped table-dark table-bordered">
	<tbody>
		<c:forEach items="${listeEmploye}" var="employe">
			<tr>
				<td><c:out value="${employe.pr}"></c:out></td>
				<td><c:out value="${employe.matricule}"></c:out></td>
				<td><c:out value="${employe.grade.code}"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>



