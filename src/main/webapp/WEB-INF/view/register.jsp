<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register User</title>
</head>
<body>
	<a href="home">Home </a>
	<h2>Register Here</h2>
	<c:if test="${not empty user}"><div><font color="Red"> <span>${user.userId}with user Id, User already exist</span></font></div></c:if>
	<table border=1>
		<form:form action="register" method="POST" commandName="register">
			<tr>
				<td>User Id</td>
				<td><input type="text" name="userId" /></td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="email" name="emailId" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td><input type="password" name="cpassword" /></td>
			</tr>
			<td colspan="2" align="center"><input type="submit"
				name="action" value="Register" /></td>
		</form:form>
	</table>
</body>
</html>
