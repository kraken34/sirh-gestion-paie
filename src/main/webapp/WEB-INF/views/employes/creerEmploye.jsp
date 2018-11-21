<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/head.jsp"></c:import>
<body>
	<div class="container">
	<h1>Ajouter un Employé</h1>
	<form:form method="POST" modelAttribute="employe">
		<div class="form-group">
			<label>Matricule :</label><form:input class="form-control" path="matricule"></form:input>
			<label>Entreprise :</label><form:select class="form-control" path="entreprise.id" items="${listeEntreprises}" itemValue="id" itemLabel="denomination"></form:select>
			<label>Profil :</label><form:select class="form-control" path="profilRemuneration.id" items="${listeProfilsRemuneration}" itemValue="id" itemLabel="code"></form:select>
			<label>Grade :</label><form:select class="form-control" path="grade.id" items="${listeGrade}" itemValue="id" itemLabel="code"></form:select>
			<div class="text-right">
				<form:button class="btn btn-primary" type="submit">Ajouter</form:button> 
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>