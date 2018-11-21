<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des bulletins</title>
</head>
<body>
	<%@include file="../menu/menu.jsp" %>
	<h1>Liste des bulletins</h1>
	<table>
		<tr><th>Date/heure création</th><th>Période</th><th>Matricule</th><th>Salaire brut</th><th>Net imposable</th><th>Net à payer</th><th>Bulletin</th></tr>
		<c:forEach items="${listBulletins}" var="bulletin">
			<tr><th><c:out value="${bulletin.date}"/></th><th><c:out value="${bulletin.periode.dateDebut}"/> --> <c:out value="${bulletin.periode.dateFin}"/></th>
			<th><c:out value="${bulletin.remunerationEmploye.matricule}"/></th><th><c:out value="${salairesBrut.get(bulletin)}"/></th>
			<th><c:out value="${salairesNet.get(bulletin)}"/></th><th><c:out value="${salairesNet.get(bulletin)}"/></th>
			<th><a href="visualiser?idBulletin=${bulletin.id}">Afficher</a></th></tr>
		</c:forEach>
	</table>
</body>
</html>