<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<div id="menu">
		<ul>
			<li><a href='<c:url value="/mvc/"></c:url>'>Accueil</a></li>
			<li><a href='<c:url value="/mvc/employes/lister"></c:url>'>Liste des employés</a></li>
			<li><a href='<c:url value="/mvc/employes/creer"></c:url>'>Créer	un employé</a></li>
			<li><a href='<c:url value="/mvc/bulletin/lister"></c:url>'>Liste des bulletins</a></li>
			<li><a href='<c:url value="/mvc/bulletin/creer"></c:url>'>Créer	un bulletin</a></li>
		</ul>
	</div>
</header>