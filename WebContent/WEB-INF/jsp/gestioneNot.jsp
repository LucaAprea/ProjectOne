<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BEGEAR</title>
</head>
<body>



 <h1>Gestione Notifiche</h1>

 <table border="2" width="70%" cellpadding="2">
  <tr>
   <th>IDNotifica</th>
   <th>Data</th>
   <th>Nome</th>
   <th>Descrizione</th>
     <th>ID ADMIN</th>
   
   <th>Download</th>
   <th>Modifica</th>
   <th>Cancella</th>
  </tr>
  <c:forEach var="notifica" items="${listaNot}">
   <tr>
    <td>${notifica.idNotifica}</td>
    <td>${notifica.data}</td>
    <td>${notifica.nome}</td>
    <td>${notifica.descrizione}</td>
    <td>${notifica.dipendente.id}</td>
      
    <td><a href="/TimeSheet/downloadNot/${notifica.idNotifica}">Download</a></td>
    <td><a href="/TimeSheet/updateNot/${notifica.idNotifica}">Modifica</a></td>
    <td><a href="/TimeSheet/deleteNot/${notifica.idNotifica}"
     onClick='return confirm("Sei sicuro di Voler Eliminare il Record?")'>Delete</a></td>
   </tr>
  </c:forEach>
 </table>

 <form:form method="POST" commandName="formNot"
  action="/TimeSheet/inserisciNot" enctype="multipart/form-data">
  <table>
   <tr>
    <td><form:label path="descrizione">Descrizione</form:label></td>
    <td><form:input path="descrizione" type="text"
      onChange="UpdateInfo();" /></td>
   </tr>
   <tr>
    <td><input type="file" name="file" /></td>
    <td><input type="submit" value="Inserisci" /></td>
   </tr>
  </table>
  <br>
  <br>
  <a href="/TimeSheet/">Torna al Main</a> 
 </form:form>

 <form:form method="POST" commandName="formNot"
  action="/TimeSheet/deleteNot" enctype="multipart/form-data"></form:form>
</body>
</html>