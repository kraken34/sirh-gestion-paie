<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/head.jsp"></c:import>
<body>
	<div class="container">
	<h1>Créer bulletin de Salaire</h1>
	<form:form method="POST" modelAttribute="bulletin">
		<div class="form-group">
			<label>Periode :</label><form:select class="form-control" path="periode.id" items="${listePeriode}" itemValue="id"></form:select>
			<label>Matricule :</label><form:select class="form-control" path="remunerationEmploye.id" items="${listeMatricule}" itemValue="id" itemLabel="matricule"></form:select>
			<label>Prime exceptionnelle :</label><form:input class="form-control" path="primeExceptionnelle"></form:input>
			<form:button class="btn btn-primary" type="submit">Creer</form:button> 
		</div>
	</form:form>
	</div>
</body>
</html>