<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../head.jsp"%>

<h1>Lister des Employés</h1>

<a type="button" class="btn btn-primary btn-sm" href="/paie/mvc/employes/creer">Ajouter
	un employé</a>
<br>
<br>
<table>
	<tr>
		<td>Date/heure création</td>
		<td>Matricule</td>
		<td>Grade</td>
	</tr>
	<c:forEach var="employe" items="${listeEmployes}">
		<tr>
			<td>${employe.dateFormat}</td>
			<td>${employe.matricule}</td>
			<td>${employe.grade.code}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="../footer.jsp"%>