<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="ContentType" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<c:forEach var="i" items="${param}">
			<li><c:out value="${i.key}" /> <c:out value="${i.value}" /></li>
		</c:forEach>
	</ul>
	<c:set var="sessionPar" value="25" />
	<c:out value="${sessionPar}" />
	
	<br/>
	<%
		for (String paramKey : request.getParameterMap().keySet()) {
			out.println("Key:" + paramKey);
			for (String value : request.getParameterMap().get(paramKey)) {
				out.println("Values:" + value);
			}
		}
	%>

</body>
</html>

