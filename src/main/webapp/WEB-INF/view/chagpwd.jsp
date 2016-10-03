<%@ include file="/WEB-INF/view/header.jsp"%>
<table>
	<form:form commandName="changePassword" method="POST" action="changePassword">
		<tr>
			<td> <label>User Name </label></td> <td><input type="text" readonly="readonly" name="userName" value="${currentUser}"> 
		</tr>
		<tr> 
			<td> <label>Old Password </label></td> <td><input type="password" name="password"></td>
		</tr>
		<tr> 
			<td> <label>New Password </label></td> <td><input type="password" name="newPassword"></td>
		</tr>
		<tr> 
			<td> <label>Confirm New Password </label></td> <td><input type="password" name="cnewPassword"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Change Password"></td> 
		</tr>
	</form:form>
</table>