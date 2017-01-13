<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form method="POST" commandName="formNot"
  action="/TimeSheet/updateNot" >
  
  <table>
   <tr>
    <td><form:label path="idNotifica">Id</form:label></td>
    <td><form:input path="idNotifica" readonly="true" /></td>
   </tr>
   <tr>
    <td><form:label path="data">Data</form:label></td>
    <td><form:input path="data" readonly="true" /></td>
   </tr>
   <tr>
    <td><form:label path="nome">nome</form:label></td>
    <td><form:input path="nome" readonly="true" /></td>
   </tr>
   <tr>
    <td><form:label path="descrizione">descrizione</form:label></td>
    <td><form:input path="descrizione" /></td>
   </tr>
   
   

   <tr>
    <td colspan="2"><input type="submit" value="Modifica" /></td>
   </tr>
  </table>
  <br>
  <br>
  <a href="/TimeSheet/">Torna alla pagina principale</a>
 </form:form>

</body>
</html>