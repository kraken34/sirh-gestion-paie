<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="../head.jsp?title=Crée Employer"></c:import>
<body>
	<main class="container"> <c:import url="../menu.jsp"></c:import>
	<h1>Ajouter un employer</h1>
	<c:if test="${not empty error}">
		<c:out value="${error}"></c:out>
	</c:if> 
	<form:form method="post" modelAttribute="employe">
		<div class="form-group">
			<label for="matricule">Matricule</label>
			<form:input cssClass="form-control" id="matricule" path="matricule" />
		</div>
		<div class="form-group">
			<label for="entreprise">Entreprise</label>
			<form:select cssClass="form-control" id="entreprise"
				path="entreprise.id" items="${listeEntreprises}" itemValue="id"
				itemLabel="denomination"></form:select>
		</div>
		<div class="form-group">
			<label for="profil">Profil</label>
			<form:select cssClass="form-control" id="profil"
				path="profilRemuneration.id" items="${listProfil}" itemValue="id"
				itemLabel="code"></form:select>
		</div>
		<div class="form-group">
			<label for="grade">Grade</label>
			<form:select cssClass="form-control" id="grade" path="grade.id"
				items="${listGrade}" itemValue="id" itemLabel="code"></form:select>
		</div>
		<form:button class="btn btn-primary">Ajouter</form:button>
	</form:form> </main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>
