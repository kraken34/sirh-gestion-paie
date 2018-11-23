<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../../head.jsp"%>
</head>

<body>
	<h1>Créer Bulletin</h1>

	<header>
		<%@include file="../../menu.jsp"%>
	</header>

	<div class="container">
		<div class="row justify-content-around align-items-center">
			<div class="col-12 col-md-offset-2 col-md-8 text-center">
				<form:form method="POST" modelAttribute="bulletinSalaire">


					<label>Période du Bulletin :</label>
					<form:select path="periode.id" items="${listePeriodes}"
								itemLabel="libelle" itemValue="id" />
					<br />

					<label>Matricule de l'employé : </label>
					<form:select path="remunerationEmploye.id" items="${listeEmployes}"
								itemLabel="matricule" itemValue="id"/>
					<br />
					<label>Prime exceptionnelle :</label>
					<form:input path="primeExceptionnelle"/>
					<br />
					
					<br />
					<input type="submit" value="Soumettre" />

				</form:form>
			</div>
		</div>
	</div>


	<%@include file="../../finBody.jsp"%>
</body>
</html>
