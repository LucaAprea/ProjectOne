<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="NewFile.css">
<meta charset="ISO-8859-1">
<title>Test Servlet</title>
</head>
<body>
<div id="main">
<form  action="/TimeSheet/login" method="POST" commandName="formDipendente">
  Username:<br>
  <input type="text" name="username"><br>
  <br>
  Password:<br>
<input type="password" name="password"><br>
  <br>
  
  <input type="submit" value="Invio">
</form>
<a href="dipendenteIns">Aggiungi Dipendente</a>
	<a href="dipendenteLista">Visualizza Dipendente</a>
	<a href="/TimeSheet/gestioneNot">Visualizza Notifiche</a>
</div>





</body>
</html>