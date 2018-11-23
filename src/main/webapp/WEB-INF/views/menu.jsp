<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href=""
				id="navbarDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Employer </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/mvc/employes/list">List</a> <a
						class="dropdown-item" href="/mvc/employes/creer">Creer</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href=""
				id="navbarDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Bulletin </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/mvc/bulletinsalaire/creer">List</a> <a
						class="dropdown-item" href="/mvc/bulletinsalaire/list">Creer</a>
				</div></li>
		</ul>
	</div>
</nav>
