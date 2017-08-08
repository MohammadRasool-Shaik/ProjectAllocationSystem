<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Allocation System Login</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/projectAllocation.css"/>"/>
</head>
<body>
	<h2 align="center">Login Here</h2>
	<c:if test="${not empty user}">
		<div class="${user.statusDto.statusCode.value eq 1?'success':'error'}">
			 <span>${user.statusDto.statusMessage}</span>
		</div>
	</c:if>
	<div id="forgotPWDErrorMessage" style="display: none; margin:auto;"></div>
	<div class="displayTable">
		<form id="loginForm" action="j_spring_security_check" method="POST">
			<div class="displayRow">
				<div class="displayCell">UserName:</div>
				<div class="displayCell"><input id="username" name="j_username" type="text" /></div>
			</div>
			<div class="displayRow">
				<div class="displayCell">Password:</div>
				<div class="displayCell"><input id="password" name="j_password" type="password" /></div>
			</div>
			<div class="displayRow">
				<div class="displayCell"><a href="<c:url value='register'></c:url>"> Register</a></div>
				<div class="displayCell"><input type="submit" value="Login" /></div>
			</div>
			<div class="displayRow"><div class="displayCell"><a href="#" id="forgotpwdButton">Forgot your password?</a></div></div>
		</form>
	</div>
	<form class="displayTable" style="display: none" id="forgotpasswordForm">
		<div class="displayRow">
			<div class="displayCell">Email Id</div><div class="displayCell"> <input type="email" id="emailId" name="emailId" /> <input type="hidden" id="forgotPasswordURL" value="${pageContext.request.contextPath}" name="emailId" /> </div>
		</div>
		<div class="displayRow">
			<div class="displayCell"></div><div class="displayCell" align="right"> <input id="forgotPWD" type="submit" value="Email Password"></div>
		</div>
	</form>
	
				
			
	<font color="Red"> <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
	
	<%@ include file="/WEB-INF/view/jsincludes.jsp"%>
	
	
</body>
</html>