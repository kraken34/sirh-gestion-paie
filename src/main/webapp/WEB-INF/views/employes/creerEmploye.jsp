<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<form:form method="POST" modelAttribute="employe">

	<form:input path="matricule"/>
	
	<form:select path="entreprise.id" items="${listeEntreprises}" itemLabel="denomination" itemValue="id"></form:select>

	<input type="submit" value="Enregistrer">
</form:form>

<h1>Créer Employe</h1>
<p>Préfixe Matricule : ${listeCotisations}</p>