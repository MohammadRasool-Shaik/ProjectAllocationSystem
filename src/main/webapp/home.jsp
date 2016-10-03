<%@ include file="/WEB-INF/view/includes.jsp"%>
<html>
<body>
<security:authentication property="name"/>
<security:authentication property="authorities"/>
<a href="logout">logout</a>

<h1>
	<security:authorize access="hasRole('admin')" >
		Admin
	</security:authorize>
	<security:authorize access="hasRole('user')">
		User
	</security:authorize>
</h1>
<h2>Forgot Password</h2>
<form:form commandName="forgotPassword" method="POST" action="mail">
	<input type="text" name="mailId">
	<!-- <input type="text" name="content"> -->
	<input type="submit" value="Send"/>
</form:form>

<a href="usergroupmgt">UserGroup Management</a>	


</body>
</html>
