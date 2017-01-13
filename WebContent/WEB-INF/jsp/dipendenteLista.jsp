<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Dipendente</title>
</head>
<body>
	<h1>Lista Dipendenti</h1>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Codice Fiscale</th>
			<th>UserName</th>
			<th>Password</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="dip" items="${list}">
			<tr>
				<td>${dip.id}</td>
				<td>${dip.nome}</td>
				<td>${dip.cognome}</td>
				<td>${dip.cf}</td>
				<td>${dip.username}</td>
				<td>${dip.password}</td>
				<td><a href="updDipendente/${dip.id}">Edit</a></td>
				
				<!--  <td><button type="submit" onClick='confirm("Sei sicuro etcetc?")'>Delete </button></td> -->
				<td><a href="deleteDipendente/${dip.id}"
					onClick='return confirm("Sei sicuro di Voler Eliminare il Record?")'>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="/TimeSheet/dipendenteIns">Aggiungi Dipendente</a>
	<a href="/TimeSheet/gestioneNot">Lista Notifiche</a>
</body>
</html>