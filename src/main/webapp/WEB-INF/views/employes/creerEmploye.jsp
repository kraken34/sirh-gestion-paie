<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Créer Employe</h1>

<form:form method="POST" modelAttribute="employe">
	
	<label>Matricule de l'employé :</label>
	<form:input path="matricule"/>
	<br/>
	<label>Entreprise de l'employé :</label>
	<form:select path="entreprise.id" items="${listeEntreprises}"  itemLabel="denomination" itemValue="id"/>
	<br/>
	<label>Profil de l'employé :</label>
	<form:select path="profilRemuneration.id" items="${listeProfiles}" itemLabel="code" itemValue="id"/>
	<br/>
	<label>Grade de l'employé :</label>
	<form:select path="grade.id" items="${listeGrades}" itemLabel="code" itemValue="id"/>
	
	<input type ="submit" value="Soumettre"/>
	
</form:form>

<p>Préfixe Matricule : ${prefixMatricule}</p>