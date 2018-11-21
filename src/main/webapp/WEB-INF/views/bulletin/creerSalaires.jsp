<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" modelAttribute="bulletin">




    <table>
        
        <tr>
            <td>Periode</td>
            <td><form:select path="periode.id" items="${listePeriodes}" itemValue="id" itemLabel="dateAffichee"></form:select></td>
        </tr>
        <tr>
            <td>Matricule</td>
            <td><form:select path="remunerationEmploye.id" items="${listeMatricules}" itemValue="id" itemLabel="matricule"></form:select></td>
        </tr>
        <tr>
            <td>Prime Exceptionnelle</td>
            <td><form:input path="primeExceptionnelle"></form:input></td>
        </tr>
    </table>
    <form:button type="submit">Envoyer</form:button>
</form:form>