<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value="/styles/projectAllocation.css"/>"/>
</head>
<body>
	<a href="home">Home </a>
	<h2>Forgot Password</h2>
	<c:if test="${not empty user}">
		<div class="error">
			<span>${user.statusDto.statusMessage}</span>
		</div>
	</c:if>
	<form id="forgotpasswordForm" action="forgotPassword"  method="POST">
		<table>
			<tr>
				<td>
					<label for="emailId">Email</label></td><td> <input type="text" id="emailId" name="emailId" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="Send">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/WEB-INF/view/jsincludes.jsp"%>
</body>
</html>
