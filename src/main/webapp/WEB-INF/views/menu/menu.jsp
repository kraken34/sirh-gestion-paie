<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<div id="menu">
		<ul>
			<li><a href='<c:url value="/mvc/"></c:url>'>Accueil</a></li>
			<li><a href='<c:url value="/mvc/employes/creer"></c:url>'>Créer Employés</a></li>
			<li><a href='<c:url value="/mvc/employes/lister"></c:url>'>Lister employés</a></li>
			<li><a href='<c:url value="/mvc/bulletin/creer"></c:url>'>Créer bulletins</a></li>
			<li><a href='<c:url value="/mvc/bulletin/lister"></c:url>'>Lister bulletins</a></li>
			<li><a href='<c:url value="/mvc/bulletin/visualiser"></c:url>'>Visualiser bulletins</a></li>
		</ul>
	</div>
</header>
