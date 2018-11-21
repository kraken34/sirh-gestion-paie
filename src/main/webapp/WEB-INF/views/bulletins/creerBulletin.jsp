<%@include file="../head.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Créer un Bulletin</h1>

<form:form method="post" modelAttribute="bulletin">

	<!-- ici c'est la propriété `denomination` de chaque entreprise qui sera affiché à l'utilisateur -->
	<form:select path="periode.id" items="${listePeriodes}" itemValue="id"
		itemLabel="DateDebutFin"></form:select>

	<form:select path="remunerationEmploye.id" items="${listeEmployes}"
		itemValue="id" itemLabel="matricule"></form:select>

	<form:input path="primeExceptionnelle" id="primeExceptionnelle" />
	<input type="submit" value="Valider">

</form:form>
<%@ include file="../footer.jsp"%>