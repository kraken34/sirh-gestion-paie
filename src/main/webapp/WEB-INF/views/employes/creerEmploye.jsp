<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Cr�er Employe</h1>

<form:form method="POST" modelAttribute="employe">

	<form:input path="matricule"/>
	
	<!-- Menu d�roulant des entreprises -->
	<form:select path="entreprise.id" items="${listeEntreprises}" itemLabel="denomination" itemValue="id"></form:select>

	<!-- Menu d�roulant des professions -->
	<form:select path="profilRemuneration.id" items="${listeProfils}" itemLabel="code" itemValue="id"></form:select>

	<!-- Menu d�roulant des grades -->
	<form:select path="grade.id" items="${listeGrade}" itemLabel="libelle" itemValue="id"></form:select>
	
	<input type="submit" value="Enregistrer">
</form:form>