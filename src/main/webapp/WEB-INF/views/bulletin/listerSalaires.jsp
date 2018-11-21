<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PAIE</title>
</head>
<body>
	<ul>
		<c:forEach var="salaire" items="${mapBulletinCalcul}">

			<li>
				<c:out value="${salaire.key.remunerationEmploye.creation}"/>
				<c:out value="${salaire.key.periode.dateAffichee}"/>
				<c:out value="${salaire.key.remunerationEmploye.matricule}"/>
				<c:out value="${salaire.value.salaireBrut}"/>
				<c:out value="${salaire.value.netImposable}"/>
				<c:out value="${salaire.value.netAPayer}"/>
			</li>

		</c:forEach> 
	</ul>
</body>
</html>