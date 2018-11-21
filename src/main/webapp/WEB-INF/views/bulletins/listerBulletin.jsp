<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../head.jsp"%>

<h1>Lister des Bulletins</h1>

<a type="button" class="btn btn-primary btn-sm"
	href="/paie/mvc/bulletins/creer">Ajouter un bulletin</a>
<br>
<br>
<table>
	<tr>
		<td>Date/heure création</td>
		<td>Période</td>
		<td>Matricule</td>
	</tr>
	<c:forEach var="bulletin" items="${listeBulletins}">
		<tr>
			<td>${bulletin.dateFormat}</td>
			<td>${bulletin.periode.dateDebutFin}</td>
			<td>${bulletin.remunerationEmploye.matricule}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="../footer.jsp"%>