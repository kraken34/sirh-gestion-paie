<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ajouter un employe </title>
</head>
<body>
	
	<h1>     creer un employé </h1>
	<nav class="navbar navbar-default">
		<div class="collapse navbar-collapse" id="maNavBar">
			<ul class="nav navbar-nav">
				<li><a href='<c:url value="/mvc/employes/lister/"></c:url>'><span
						aria-hidden="true"></span> Employés</a></li>
				<li><a href='<c:url value="/mvc/bulletins/creer/"></c:url>'><span
					aria-hidden="true"></span> Bulletin </a></li>
		</ul>
		</div>
	</nav>
	
	<form:form method="post" modelAttribute="employe">

    <form:input path="matricule" />

    <!-- ici c'est la propriété `denomination` de chaque entreprise qui sera affiché à l'utilisateur -->
    
    <form:select path="entreprise.id" items="${entreprises}"
        itemValue="id" itemLabel="denomination"></form:select>

    <form:select path="profilRemuneration.id" items="${profils}"
        itemValue="id" itemLabel="code"></form:select>

    <form:select path="grade.id" items="${grades}" itemValue="id"
        itemLabel="code"></form:select>
        <input type="submit" value="Valider">
        
</form:form>
	
	

</body>
</html>
