<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value="/styles/projectAllocation.css"/>"/>
</head>
<body>
	<a href="home">Home </a>
	<h2 align="center">Register Here</h2>
	<c:if test="${not empty user}">
		<div class="">
			<font color="Red"> <span>${user.statusDto.statusMessage}</span></font>
		</div>
	</c:if>
	<div style="margin:auto;">
		<form:form id="registertrationForm" action="register" method="POST" commandName="user">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<div class="displayTable">
				<div class="displayRow">
					
					<div class="displayCell">Enter User Name</div>
					<div class="displayCell">
						<form:input type="text" id="userName" placeholder="Ex: rasool.shaik" valid="" path="userName" autocomplete="off"/>
						<span style=" color: grey; ">@gmail.com</span>
						<div class="false" id="validUser" style="display:none;"></div>
					</div>
				</div>
				<div class="displayRow">
					<div class="displayCell">User Id</div>
					<div class="displayCell">
						<input type="text" id="userId" placeholder="Enter Your EmployeeId" name="userId" autocomplete="off"/>
					</div>
				</div>
				<%-- <div class="displayRow">
					<div class="displayCell">Email Id</div>
					<div class="displayCell"><form:input type="text" path="emailId" placeholder="Ex: rasool1988@gmail.com" /></div>
				</div> --%>
				<div class="displayRow">
					<div class="displayCell">Password</div>
					<div class="displayCell"><form:input type="password" path="password" id="password" placeholder="Password" autocomplete="off" /></div>
				</div>
				<div class="displayRow">
					<div class="displayCell">Confirm Password</div>
					<div class="displayCell"><form:input type="password" path="cpassword"  placeholder="Re-type Password" autocomplete="off"/></div>
				</div>
				<div class="displayRow">
					<div class="displayCell"><input type="submit" name="action" value="Register" />
					</div>
				</div>
			</div>
		</form:form>
	</div> 
	<%@ include file="/WEB-INF/view/jsincludes.jsp"%>
</body>
</html>
