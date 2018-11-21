<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="../head.jsp?title=Crée Bulletin"></c:import>
<body>
	<main class="container"> 
		<h1>Crée Bulletin de Salaire d'un employé</h1>
		<form:form method="post" modelAttribute="bulletin">
		
		<div class="form-group">
			<label for="periode-lablel">Periode</label>
			<form:select cssClass="form-control" id="periode-lablel" path="periode.id"
				items="${listePeriode}" itemValue="id" itemLabel="periodeString"></form:select>
		</div>
		<div class="form-group">
			<label for="matri-label">Matricule</label>
			<form:select cssClass="form-control" id="matri-label" path="remunerationEmploye.id"
				items="${listeEnploye}" itemValue="id" itemLabel="matricule"></form:select>
		</div>
		<div class="form-group">
			<label for="prim-label">Prime exceptionnelle</label>
			<form:input cssClass="form-control" id="prim-label" path="primeExceptionnelle" />
		</div>
		<form:button class="btn btn-primary">Crée</form:button>
	</form:form> </main>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>
