<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<div id="menu">
		<ul>
			<li><a href='<c:url value="/mvc/"></c:url>'>Accueil</a></li>
			<li><a href='<c:url value="/mvc/employes/creer"></c:url>'>Cr�er Employ�s</a></li>
			<li><a href='<c:url value="/mvc/employes/lister"></c:url>'>Lister employ�s</a></li>
			<li><a href='<c:url value="/mvc/bulletin/creer"></c:url>'>Cr�er bulletins</a></li>
			<li><a href='<c:url value="/mvc/bulletin/lister"></c:url>'>Lister bulletins</a></li>
			<li><a href='<c:url value="/mvc/bulletin/visualiser"></c:url>'>Visualiser bulletins</a></li>
		</ul>
	</div>
</header>
