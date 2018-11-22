<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../../head.jsp"%>
</head>

<body>
	<h1>Créer Employe</h1>

	<header>
		<%@include file="../../menu.jsp"%>
	</header>

	<div class="container">
		<div class="row justify-content-around align-items-center">
			<div class="col-12 col-md-offset-2 col-md-8 text-center">
				<form:form method="POST" modelAttribute="employe">

					<label>Matricule de l'employé :</label>
<%-- 					<form:input path="matricule" /> --%>
					<form:select path="matricule" items="${listeMatricules}" />
					<br />
					<label>Entreprise de l'employé :</label>
					<form:select path="entreprise.id" items="${listeEntreprises}"
						itemLabel="denomination" itemValue="id" />
					<br />
					<label>Profil de l'employé :</label>
					<form:select path="profilRemuneration.id" items="${listeProfiles}"
						itemLabel="code" itemValue="id" />
					<br />
					<label>Grade de l'employé :</label>
					<form:select path="grade.id" items="${listeGrades}"
						itemLabel="libelle" itemValue="id" />
					<br />
					<input type="submit" value="Soumettre" />

				</form:form>
			</div>
		</div>
	</div>


	<%@include file="../../finBody.jsp"%>
</body>
</html>
