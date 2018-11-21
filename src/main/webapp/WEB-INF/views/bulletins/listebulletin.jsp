<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>liste des bulletins</title>
</head>
<body>
<h1>  liste des bulletins     </h1>
        <table>
            <tr>
                <th > Date/Heure création </th>
                <th > Période </th>
                <th > Matricule </th>
                <th > Salaire Brut </th>
                <th > Net Imposable </th>
                <th > Net A Payer </th>
                <th > Actions </th>
            </tr>   
 </table>

<c:forEach var="map" items="${map}">

<tr>

<td> </td>

<td> ${map.key.periode.dateDebut} - ${map.key.periode.dateFin} </td>

<td> ${map.key.remunerationEmploye.matricule} </td>

<td> ${map.value.salaireBrut} </td>

<td> ${map.value.netImposable} </td>

<td> ${map.value.netAPayer}</td>

</tr>

</c:forEach>	

		

</body>
</html>