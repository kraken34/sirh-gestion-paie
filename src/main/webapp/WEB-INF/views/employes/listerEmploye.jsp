<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PAIE</title>
</head>
<body>
	<ul>
		<c:forEach var="employe" items="${listemploye}">

			<li>
			<c:out value="${employe.creation}"/>
				<c:out value="${employe.matricule}"/>
				<c:out value="${employe.grade.code}"/></li>

		</c:forEach>
	</ul>
</body>
</html>