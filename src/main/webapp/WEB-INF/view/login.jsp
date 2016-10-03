<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Allocation System Login</title>
</head>
<body>
	<h2>Login Here</h2>
	<c:if test="${not empty registermsg}">${registermsg}</c:if>
	<c:if test="${not empty user}"> Hi ${user.userName}, You Registered Successfully, Login Now </c:if>
	<form action="j_spring_security_check" method="POST">
		<table>
			<thead>Prove Yourself, Whom You Are?
			</thead>
			<tr>
				<td>UserName:</td>
				<td><input name="j_username" type="text" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="j_password" type="password" /></td>
			</tr>
			<tr>
				<td><a href="<c:url value='getregister'></c:url>"> Register</a></td>
				<td><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form>

	<form:form action="forgotPassword" commandName="forgotPassword"
		method="POST">
		Email <input type="text" name="emailId" />
		<input type="submit" value="forgot password">
	</form:form>
	<font color="Red"> <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
</body>
</html>