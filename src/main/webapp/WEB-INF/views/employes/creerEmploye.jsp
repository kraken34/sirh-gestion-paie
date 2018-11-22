<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" modelAttribute="employe">




    <table>
        <tr>
            <td>Matricule</td>
            <td><form:input path="matricule"></form:input></td>
        </tr>
        <tr>
            <td>Entreprise</td>
            <td><form:select path="entreprise.id" items="${listeEntreprises}" itemValue="id" itemLabel="denomination"></form:select></td>
        </tr>
        <tr>
            <td>Profil</td>
            <td><form:select path="profilRemuneration.id" items="${listeProfils}" itemValue="id" itemLabel="code"></form:select></td>
        </tr>
        <tr>
            <td>Grade</td>
            <td><form:select path="grade.id" items="${listeGrades}" itemValue="id" itemLabel="code"></form:select></td>
        </tr>
        
    </table>
    <form:button type="submit">Envoyer</form:button>
</form:form>