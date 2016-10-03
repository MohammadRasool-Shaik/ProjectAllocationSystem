<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="j_spring_security_check" method="POST">
		<table>
			<thead> Prove Yourself, Whom You Are?</thead>
			<tr>
				<td>UserName: </td><td><input name="j_username" type="text"/></td>
			</tr>
			<tr>
				<td>Password: </td><td><input name="j_password" type="password"/></td>
			</tr>
			<tr><td colspan="2" align="right"><input type="submit" value="Login"/></td> </tr>
		</table>
	</form>
	<font color="Red">
		<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
</body>
</html>