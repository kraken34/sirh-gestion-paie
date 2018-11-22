<%@include file="../head.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Ajouter un Employ�</h1>
<!-- <p>Pr�fixe Matricule : ${prefixMatricule}</p> -->

<form:form method="post" modelAttribute="employe">

	<form:input path="matricule" />

	<!-- ici c'est la propri�t� `denomination` de chaque entreprise qui sera affich� � l'utilisateur -->
	<form:select path="entreprise.id" items="${listeEntreprises}"
		itemValue="id" itemLabel="denomination"></form:select>

	<form:select path="profilRemuneration.id" items="${listeProfils}"
		itemValue="id" itemLabel="code"></form:select>

	<form:select path="grade.id" items="${listeGrades}" itemValue="id"
		itemLabel="code"></form:select>
		<input type="submit" value="Valider">
		
</form:form>
<%@ include file="../footer.jsp"%>