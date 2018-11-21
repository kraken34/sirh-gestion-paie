<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>créer un bulletin </title>
</head>
<body>


<h1>            créer un bulletin          </h1>
<nav class="navbar navbar-default">
		<div class="collapse navbar-collapse" id="maNavBar">
			<ul class="nav navbar-nav">
				<li><a href='<c:url value="/mvc/employes/creer/"></c:url>'><span
						aria-hidden="true"></span> Employés</a></li>
				<li><a href='<c:url value="/mvc/employes/lister/"></c:url>'><span
					aria-hidden="true"></span> Bulletin </a></li>
		</ul>
		</div>
	</nav>

<form:form method="post" modelAttribute="bulletin">
    <form:select path="periode.id" items="${periode}"
        itemValue="id" itemLabel="dateAffichee" ></form:select>

    <form:select path="remunerationEmploye.id" items="${matricule}"
        itemValue="id" itemLabel="matricule"></form:select>

    <form:input  path="primeExceptionnelle" id="primeExceptionnelle"
        ></form:input>
        <input type="submit" value="Valider">
        
</form:form>
	

</body>
</html>