<%@ include file="/WEB-INF/view/includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Group Management</title>
</head>
<body>
	<table border="1">
	
		<thead align="center">User Group Management</thead>
		<tr><th>groupId</th><th>Group Name</th><th>Action</th></tr>
		<c:forEach items="${userGroupList}" var="group">
			<tr>
				<form:form action="usergroupactions" method="POST" commandName="usergroupactions_${group.groupId}">
					<td><input type="text" name="groupId" value="${group.groupId}" readonly="readonly"/></td>
					<td><input type="text" name="groupName" value="${group.groupName}"/></td>
					<td colspan="2">
						<input type="submit" name="action" value="Edit"/>
						<input type="submit" name="action" value="Delete" />
					</td>
				</form:form>
			</tr>
		</c:forEach>
		<tr>
			<form:form action="usergroupactions" method="POST" commandName="usergroupactions">
				<td><input type="text" name="groupId" /></td>
				<td><input type="text" name="groupName" /></td>
				<td colspan="1"><input type="submit" name="action" value="Add" /></td>
			</form:form>
		</tr>
	</table>
</body>
</html>